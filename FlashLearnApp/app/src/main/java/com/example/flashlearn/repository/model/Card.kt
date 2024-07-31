package com.example.flashlearn.repository.model

import androidx.room.Entity

@Entity
data class Card(
    val id: Int = 0,
    val front: String,
    val back: String
)