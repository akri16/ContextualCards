package com.akribase.contextualcards.data

import com.akribase.contextualcards.models.data.CardGroup
import com.akribase.contextualcards.models.data.CardGroupsResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BackendService {

    @GET("/v3/04a04703-5557-4c84-a127-8c55335bb3b4")
    suspend fun getUISpecs(): CardGroupsResponse

    companion object {
        var BASE_URL = "https://run.mocky.io"

        fun create(): BackendService {
            val gson = GsonBuilder().apply {
                setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            }.create()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(BackendService::class.java)
        }
    }

}