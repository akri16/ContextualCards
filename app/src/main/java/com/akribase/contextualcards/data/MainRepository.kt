package com.akribase.contextualcards.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.akribase.cardcomponent.models.data.CardGroup
import com.akribase.cardcomponent.models.data.DesignType
import com.akribase.contextualcards.models.SavedHC3
import com.akribase.contextualcards.dataStore
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val service = BackendService.create()
    private val gson = Gson()
    private val dataStore = context.dataStore
    private val SAVED_CARDS = stringPreferencesKey("saved_HC3")
    private var listType: Type = object : TypeToken<List<SavedHC3>?>() {}.type

    suspend fun getUI(
        shouldFetchRemind: Boolean = false
    ): RepoResult<List<CardGroup>> = safeIoCall {

        val cardGroups = service.getUISpecs().cardGroups.toMutableList()
        val savedCards = dataStore.data.map {
            gson.fromJson<List<SavedHC3>>(it[SAVED_CARDS], listType)
        }.first()?.toMutableList()

        withContext(Dispatchers.Default) {
            savedCards?.let {
                val cardNames = savedCards.map { it.card.name }
                cardGroups.replaceAll { cardGroup ->
                    val cards = cardGroup.cards.toMutableList()
                    if (cardGroup.designType == DesignType.HC3) cards.removeAll { it.name in cardNames }
                    cardGroup.copy(cards = cards)
                }

                cardGroups.removeIf { it.cards.isEmpty() }

                if (shouldFetchRemind) {
                    val remindCards = savedCards.filter { it.remind }.map { it.card }
                    if (remindCards.isNotEmpty()) cardGroups.add(
                        0,
                        CardGroup(-1, "Remind Cards", DesignType.HC3, remindCards, true)
                    )
                }
            }
            cardGroups
        }
    }

    suspend fun addToPref(savedCard: SavedHC3) {
        dataStore.edit { preferences ->
            val cards =
                gson.fromJson<List<SavedHC3>?>(preferences[SAVED_CARDS], listType)?.toMutableList()
                    ?: mutableListOf()
            cards.removeIf { it.card.name == savedCard.card.name }
            cards.add(savedCard)
            preferences[SAVED_CARDS] = gson.toJson(cards)
        }
    }

    private suspend fun <T> safeIoCall(call: suspend () -> T) = safeIoCall(Dispatchers.IO, call)
}