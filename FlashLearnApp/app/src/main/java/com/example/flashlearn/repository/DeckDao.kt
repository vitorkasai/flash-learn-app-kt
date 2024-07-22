package com.example.flashlearn.repository

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DeckDao {

    //@Query("SELECT * FROM Card ORDER BY time ASC LIMIT 1")
    //suspend fun getBestScore(): Score

    @Query("SELECT * FROM Card ORDER BY includeDateTime ASC")
    fun getAllScores(): Flow<List<Card>>

    @Query("DELETE FROM Card")
    suspend fun deleteAllScores()
}