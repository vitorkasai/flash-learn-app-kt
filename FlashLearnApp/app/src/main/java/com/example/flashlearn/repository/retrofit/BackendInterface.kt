package com.example.flashlearn.repository.retrofit

import com.example.flashlearn.repository.model.Deck
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BackendInterface {
    @GET("deck")
    suspend fun getAllDecks(): Response<List<Deck>>

    @POST("deck")
    suspend fun createDeck(@Body requestBody: RequestBody): Response<Void>

    @POST("card")
    suspend fun createCard(@Body requestBody: RequestBody): Response<Void>
}