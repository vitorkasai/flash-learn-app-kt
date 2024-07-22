package com.example.flashlearn.compose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn.revision.RevisionConfig
import com.example.flashlearn.revision.Number
import com.example.flashlearn.repository.CardRepository
import kotlinx.coroutines.launch
import java.lang.String
import java.text.SimpleDateFormat
import java.util.Date


class RevisionViewModel(private val cardRepository: CardRepository) : ViewModel() {

    init {
        startRevision()
    }

    fun startRevision() {

    }








    private val revisionConfig = RevisionConfig()
    private var current = 1
    private var startTime : Long = 0
    private var yourTime = -1.0f
    var dialogTimeMessage = ""
    var markers = mutableStateListOf<Boolean>()
    var numbers = mutableStateListOf<Number>()
    var showEndGameDialog by mutableStateOf(false)
    var endGameDialogText by mutableStateOf("")
    var playerName by mutableStateOf("")

    init {
        startRevision()
    }

    fun startRevision() {
        revisionConfig.reset()
        startTime = System.currentTimeMillis()
        startRound()
    }

    fun startRound() {
        current = 1
        markers.clear()
        repeat(9) {
            markers.add(false)
        }
        numbers.clear()
        revisionConfig.getNextConfiguration().forEach { numbers.add(it) }
    }

    fun click(buttonIndex: Int, value: Int) {
        if (value == current) {
            // change to green
            current++
            markers[buttonIndex] = true
            Log.i("Game", "current: " + current)
            Log.i("Game", "" + markers.toString())
            if (current == 10) {
                if (revisionConfig.hasNext()) {
                    startRound()
                } else {
                    endGame()
                }
            }
        }
    }

    fun endGame() {
        playerName = ""
        yourTime = (System.currentTimeMillis() - startTime) / 1000.0f
        viewModelScope.launch {
            val bestScore = cardRepository.getBestScore()
            var isBest = ""
            if (bestScore == null || bestScore.time > yourTime)
                isBest = "NEW RECORD!!!\n"
            val message = isBest + String.format(dialogTimeMessage, yourTime)
            endGameDialogText = message
            showEndGameDialog = true
        }
    }

    fun closeDialog() {
        showEndGameDialog = false
        val whenPlayed = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())
        // save player data
        val player = playerName.takeIf { it.isNotEmpty() } ?: "Anonymous"
        val score = Score(
            playerName = player,
            time = yourTime,
            whenPlayed = whenPlayed)
        viewModelScope.launch {
            scoreRepository.insertScore(score)
        }
    }
}