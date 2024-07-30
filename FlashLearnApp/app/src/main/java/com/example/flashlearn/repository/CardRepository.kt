package com.example.flashlearn.repository

import com.example.flashlearn.repository.retrofit.BackendInterface
import com.example.flashlearn.repository.retrofit.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class CardRepository {
    private var client: BackendInterface = RetrofitInstance.api
    suspend fun createCard(category: String, front : String, back : String) {
        val json = JSONObject()
            .put("deckCategory", category)
            .put("front", front)
            .put("back", back)
            .toString()

        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
        val response = client.createCard(requestBody)
        if (!response.isSuccessful) {
            throw Exception("Falha ao adicionar card: ${response.message()}")
        }
    }

}