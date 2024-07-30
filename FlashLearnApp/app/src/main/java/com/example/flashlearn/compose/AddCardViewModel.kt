package com.example.flashlearn.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn.repository.CardRepository
import kotlinx.coroutines.launch

class AddCardViewModel(private val cardRepository: CardRepository) : ViewModel() {
    fun addCard(category: String, front : String, back : String) {
        viewModelScope.launch {
            try {
                cardRepository.createCard(category, front, back)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}