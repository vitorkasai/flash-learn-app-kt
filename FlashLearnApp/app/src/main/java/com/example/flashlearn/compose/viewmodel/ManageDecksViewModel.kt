package com.example.flashlearn.compose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn.repository.DeckRepository
import com.example.flashlearn.repository.model.Deck
import kotlinx.coroutines.launch

class ManageDecksViewModel(private val deckRepository: DeckRepository) : ViewModel() {
    var deckList = mutableStateListOf<Deck>()
    init {
        refreshDecks()
    }
    fun refreshDecks() {
        viewModelScope.launch {
            deckRepository.getAllDecks().collect {
                deckList.clear()
                it.forEach { deck -> deckList.add(deck) }
            }
        }
    }

    fun deleteDeck(deckId: Long) {
        viewModelScope.launch {
            deckRepository.deleteDeckById(deckId)
            refreshDecks()
        }
    }
}