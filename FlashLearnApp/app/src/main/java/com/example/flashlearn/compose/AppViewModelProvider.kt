package com.example.flashlearn.compose

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flashlearn.FlashLearnApplication


/**
 * Provides Factory to create instance of ViewModel for the entire FlashLearn app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ChoiceDeckViewModel(flashLearnApplication().container.deckRepository)
        }
        initializer {
            ManageDecksViewModel(flashLearnApplication().container.deckRepository)
        }
        initializer {
            DeckDetailViewModel(flashLearnApplication().container.cardRepository)
        }
        initializer {
            AddDeckViewModel(flashLearnApplication().container.deckRepository)
        }
        initializer {
            AddCardViewModel(flashLearnApplication().container.cardRepository)
        }
    }
}

fun CreationExtras.flashLearnApplication(): FlashLearnApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FlashLearnApplication)