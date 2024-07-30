package com.example.flashlearn.repository.retrofit

import com.example.flashlearn.repository.Deck
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface BackendInterface {
    @GET("deck")
    suspend fun getAllDecks(): Response<List<Deck>>

}