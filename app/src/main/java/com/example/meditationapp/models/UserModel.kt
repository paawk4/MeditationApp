package com.example.meditationapp.models

import com.google.gson.annotations.SerializedName

var user = UserModel("", "", "Эмиль", "https://i.imgur.com/LKuc1Pq.png", "")

data class UserModel(
    val id: String,
    val email: String,
    @SerializedName("nickName")
    val nickname: String,
    val avatar: String,
    val token: String
)

data class NotAuthUser(
    val email: String,
    val password: String
)
