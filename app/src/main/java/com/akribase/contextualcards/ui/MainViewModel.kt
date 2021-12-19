package com.akribase.contextualcards.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akribase.contextualcards.data.MainRepository
import com.akribase.contextualcards.data.RepoResult
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel: ViewModel() {
    val repo = MainRepository
    private var fetchJob: Job? = null
    val isFetching = MutableLiveData(false)
    val uiSpec = MutableLiveData<List<RenderableCardGroup>>()

    init {
        fetchUISpec()
    }

    fun fetchUISpec() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            isFetching.value = true
            repo.getUI().let { repoResult ->
                when (repoResult) {
                    is RepoResult.Success -> uiSpec.value = repoResult.res.map {
                            RenderableCardGroup.createFromCardGroup(it)
                        }
                    is RepoResult.Error -> Timber.d(repoResult.err.toString())
                }
            }
            isFetching.value = false
        }
    }
}