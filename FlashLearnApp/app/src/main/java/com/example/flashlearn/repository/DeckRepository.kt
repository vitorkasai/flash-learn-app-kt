package com.example.flashlearn.repository

import kotlinx.coroutines.flow.Flow

class CardRepository(private val cardDao: ScoreDao) {
    suspend fun insertScore(score: Score) = scoreDao.insert(score)

    suspend fun deleteAllScores() = scoreDao.deleteAllScores()

    fun getAllScores(): Flow<List<Score>> = scoreDao.getAllScores()

    suspend fun getBestScore(): Score = scoreDao.getBestScore()
}