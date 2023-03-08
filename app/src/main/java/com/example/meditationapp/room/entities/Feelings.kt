package com.example.meditationapp.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feelings")
data class FeelingEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val position: Int,
    val image: String
)