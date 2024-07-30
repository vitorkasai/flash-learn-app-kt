package com.example.flashlearn.repository.retrofit

import com.example.flashlearn.repository.model.Deck
import retrofit2.Response
import retrofit2.http.GET

interface BackendInterface {
    @GET("deck")
    suspend fun getAllDecks(): Response<List<Deck>>

}