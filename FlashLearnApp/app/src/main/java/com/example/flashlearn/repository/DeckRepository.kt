package com.example.flashlearn.repository

import com.example.flashlearn.repository.model.Deck
import com.example.flashlearn.repository.retrofit.BackendInterface
import com.example.flashlearn.repository.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

}