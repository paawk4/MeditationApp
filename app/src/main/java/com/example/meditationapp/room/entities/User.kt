package com.example.meditationapp.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val email: String,
    val nickname: String,
    val avatar: String,
    val token: String
)
