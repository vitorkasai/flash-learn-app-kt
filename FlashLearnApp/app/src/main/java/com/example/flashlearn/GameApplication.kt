package com.example.flashlearn

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.flashlearn.repository.GameDatabase
import com.example.flashlearn.repository.ScoreRepository

/**
 * REQUIREMENT:
 * You need to specify attribute android:name=".GameApplication" in AndroidManifest.xml
 * Otherwise, this class is not initialized
 */
class GameApplication : Application() {

    // instance to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        Log.i("GameApplication", "onCreate")
    }
}

/**
 * We want to limit the visibility of Android-related objects to ViewModels and Composable.
 *
 * So, we attach here the repositories to a GameApplication object
 *    so that we can retrieve them in the AppViewModelProvider.
 */
class AppContainer(private val context: Context) {
    val scoreRepository : ScoreRepository by lazy {
        ScoreRepository(GameDatabase.getDatabase(context).scoreDao())
    }
}