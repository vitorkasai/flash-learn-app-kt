package com.example.flashlearn.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Deck(
    val id: Int,
    val category: String,
    val cards: List<Card>
)