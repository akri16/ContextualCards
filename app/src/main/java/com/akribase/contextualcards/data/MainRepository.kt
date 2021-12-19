package com.akribase.contextualcards.data

import com.akribase.contextualcards.models.data.CardGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

object MainRepository {
    private val service = BackendService.create()

    suspend fun getUI(): RepoResult<List<CardGroup>> = safeApiCall { service.getUISpecs().cardGroups }

    private suspend fun <T> safeApiCall(call: suspend () -> T) = safeApiCall(Dispatchers.IO, call)
}