package com.example.flashlearn

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.flashlearn.repository.CardRepository
import com.example.flashlearn.repository.DeckRepository

/**
 * REQUIREMENT:
 * You need to specify attribute android:name=".FlashLearnApplication" in AndroidManifest.xml
 * Otherwise, this class is not initialized
 */
class FlashLearnApplication : Application() {

    // instance to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        Log.i("FlashLearnApplication", "onCreate")
    }
}

/**
 * We want to limit the visibility of Android-related objects to ViewModels and Composable.
 *
 * So, we attach here the repositories to a FlashLearnApplication object
 *    so that we can retrieve them in the AppViewModelProvider.
 */
class AppContainer(private val context: Context) {
    val deckRepository: DeckRepository by lazy {
        DeckRepository()
    }
    val cardRepository: CardRepository by lazy {
        CardRepository()
    }
}