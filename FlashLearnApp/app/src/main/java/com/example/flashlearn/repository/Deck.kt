package com.example.flashlearn.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Deck(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val cards: List<Card>,
    val includeDateTime: LocalDateTime
)