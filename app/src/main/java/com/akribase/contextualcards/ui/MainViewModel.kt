package com.akribase.contextualcards.ui

import android.app.Application
import androidx.lifecycle.*
import com.akribase.cardcomponent.models.data.CardGroup
import com.akribase.cardcomponent.ui.adapters.H3Remove
import com.akribase.contextualcards.data.MainRepository
import com.akribase.contextualcards.data.RepoResult
import com.akribase.contextualcards.models.SavedHC3
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val repo = MainRepository
    private var fetchJob: Job? = null
    val isFetching = MutableLiveData(false)
    val uiSpec = MutableLiveData<List<CardGroup>>()

    init {
        _fetchUISpec(true)
    }

    private fun _fetchUISpec(shouldFetchReminds: Boolean) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            isFetching.value = true
            repo.getUI(getApplication(), shouldFetchReminds).let { repoResult ->
                when (repoResult) {
                    is RepoResult.Success -> uiSpec.value = repoResult.res ?: listOf()
                    is RepoResult.Error -> Timber.d(repoResult.err.toString())
                }
            }
            isFetching.value = false
        }
    }

    fun fetchUISpec() {
        _fetchUISpec(false)
    }

    fun remove(h3Remove: H3Remove) {
        val uiSpec = uiSpec.value?.toMutableList()
        val cardGroup = uiSpec?.get(h3Remove.groupPos)
        val cards = cardGroup?.cards?.toMutableList()
        val card = cards?.removeAt(h3Remove.cardPos)

        if (cards.isNullOrEmpty()) {
            uiSpec?.removeAt(h3Remove.groupPos)
        } else {
            uiSpec[h3Remove.groupPos] = cardGroup.copy(cards = cards)
        }

        this.uiSpec.value = uiSpec

        card?.bgImage?.let {
            viewModelScope.launch { repo.addToPref(SavedHC3(card, h3Remove.remind), getApplication()) }
        }

    }

}