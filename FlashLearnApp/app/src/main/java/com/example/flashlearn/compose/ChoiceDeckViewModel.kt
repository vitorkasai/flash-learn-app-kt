package com.example.flashlearn.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn.repository.Card
import com.example.flashlearn.repository.CardRepository
import kotlinx.coroutines.launch

class ChoiceDeckViewModel(private val deckRepository: CardRepository: ScoreRepository) : ViewModel() {

    var cards = mutableStateListOf<Card>()

    init {
        viewModelScope.launch {
            scoreRepository.getAllScores().collect {
                cards.clear()
                it.forEach { s -> cards.add(s) }
            }
        }
    }

    fun clearAllRecords() {
        viewModelScope.launch {
            scoreRepository.deleteAllScores()
        }
    }
}