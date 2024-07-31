package com.example.flashlearn.compose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn.repository.CardRepository
import com.example.flashlearn.repository.model.Card
import kotlinx.coroutines.launch

class DeckDetailViewModel(private val cardRepository: CardRepository) : ViewModel() {
    var cardList = mutableStateListOf<Card>()

    fun refreshCards(deckCategory: String) {
        viewModelScope.launch {
            cardRepository.getCardsByCategory(deckCategory).collect {
                cardList.clear()
                it.forEach { card -> cardList.add(card) }
            }
        }
    }
}