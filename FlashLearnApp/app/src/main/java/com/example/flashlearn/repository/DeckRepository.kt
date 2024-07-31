package com.example.flashlearn.repository

import com.example.flashlearn.repository.model.Deck
import com.example.flashlearn.repository.retrofit.BackendInterface
import com.example.flashlearn.repository.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class DeckRepository {
    private var client: BackendInterface = RetrofitInstance.api

    suspend fun getAllDecks(): Flow<List<Deck>> = flow {
        val response = client.getAllDecks()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(it)
            } ?: throw Exception("Não foram encontrados decks")
        } else {
            throw Exception("Falha ao retornar decks: ${response.message()}")
        }
    }

    suspend fun createDeck(category: String) {
        val json = JSONObject().put("category", category).toString()
        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
        val response = client.createDeck(requestBody)
        if (!response.isSuccessful) {
            throw Exception("Falha ao adicionar deck: ${response.message()}")
        }
    }

    suspend fun deleteDeckById(id: Long) {
        client.deleteDeckById(id)
    }
}