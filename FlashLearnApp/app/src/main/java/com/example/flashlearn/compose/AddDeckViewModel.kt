package com.example.flashlearn.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn.repository.DeckRepository
import kotlinx.coroutines.launch

class AddDeckViewModel(private val deckRepository: DeckRepository) : ViewModel() {

    fun addDeck(category: String) {
        viewModelScope.launch {
            try {
                deckRepository.createDeck(category)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}