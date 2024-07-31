package com.example.flashlearn.repository.model

import androidx.room.Entity

@Entity
data class Deck(
    val id: Long,
    val category: String,
    val cards: List<Card>
)