package com.example.flashlearn.repository

import kotlinx.coroutines.flow.Flow

class DeckRepository(private val deckDao: DeckDao) {
    fun getAllDecks(): List<Deck> = deckDao.

    suspend fun insertScore(score: Score) = scoreDao.insert(score)

    suspend fun deleteAllScores() = scoreDao.deleteAllScores()

    fun getAllScores(): Flow<List<Score>> = scoreDao.getAllScores()

    suspend fun getBestScore(): Score = scoreDao.getBestScore()
}