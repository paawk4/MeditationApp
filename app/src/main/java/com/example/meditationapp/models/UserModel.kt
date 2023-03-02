package com.example.meditationapp.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    val id: Int,
    val email: String,
    @SerializedName("nickName")
    val nickname: String,
    val avatar: String,
    val token: String
)
