package com.example.meditationapp.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String,
    val description: String
)
