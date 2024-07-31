package com.example.flashlearn.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn.repository.model.Deck
import com.example.flashlearn.repository.DeckRepository
import kotlinx.coroutines.launch

class ChoiceDeckViewModel(private val deckRepository: DeckRepository) : ViewModel() {
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
}