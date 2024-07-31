package com.example.flashlearn.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn.repository.CardRepository
import com.example.flashlearn.repository.model.Card
import kotlinx.coroutines.launch

class DeckDetailViewModel(private val cardRepository: CardRepository) : ViewModel() {
    var cardList = mutableStateListOf<Card>()
    var deckCategory by mutableStateOf("")

    fun updateDeckCategory(category: String) {
        deckCategory = category
        refreshCards(category)
    }

    fun deleteCard(cardId: Long) {
        viewModelScope.launch {
            cardRepository.deleteCardById(cardId)
            refreshCards(deckCategory)
        }
    }

    fun refreshCards(deckCategory: String) {
        viewModelScope.launch {
            cardRepository.getCardsByCategory(deckCategory).collect {
                cardList.clear()
                it.forEach { card -> cardList.add(card) }
            }
        }
    }
}
