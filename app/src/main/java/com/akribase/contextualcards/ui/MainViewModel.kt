package com.akribase.contextualcards.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.akribase.cardcomponent.models.data.CardGroup
import com.akribase.cardcomponent.ui.adapters.H3Remove
import com.akribase.contextualcards.data.MainRepository
import com.akribase.contextualcards.data.RepoResult
import com.akribase.contextualcards.models.SavedHC3
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repo: MainRepository
) : ViewModel() {

    private var fetchJob: Job? = null
    val isFetching = MutableLiveData(false)
    val isConnectionError = MutableLiveData(false)
    val uiSpec = MutableLiveData<List<CardGroup>>()

    init {
        _fetchUISpec(true)
    }

    private fun _fetchUISpec(shouldFetchReminds: Boolean) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            isFetching.value = true
            repo.getUI(shouldFetchReminds).let { repoResult ->
                when (repoResult) {
                    is RepoResult.Success -> {
                        uiSpec.value = repoResult.res ?: listOf()
                        isConnectionError.value = false
                    }
                    is RepoResult.Error -> {
                        uiSpec.value = listOf()
                        Timber.d(repoResult.err.toString())
                        isConnectionError.value = true
                    }
                }
            }
            isFetching.value = false
        }
    }

    fun fetchUISpec() {
        _fetchUISpec(false)
    }

    fun remove(h3Remove: H3Remove) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val uiSpec = uiSpec.value?.toMutableList()
                val cardGroup = uiSpec?.get(h3Remove.groupPos)
                val cards = cardGroup?.cards?.toMutableList()
                val card = cards?.removeAt(h3Remove.cardPos)

                if (cards.isNullOrEmpty()) {
                    uiSpec?.removeAt(h3Remove.groupPos)
                } else {
                    uiSpec[h3Remove.groupPos] = cardGroup.copy(cards = cards)
                }

                withContext(Dispatchers.Main) {
                    this@MainViewModel.uiSpec.value = uiSpec
                    card?.bgImage?.let {
                        repo.addToPref(SavedHC3(card, h3Remove.remind))
                    }
                }
            }
        }
    }
}