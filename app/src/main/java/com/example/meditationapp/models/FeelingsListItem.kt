package com.example.meditationapp.models

data class FeelingsListResponse(val data: List<FeelingsListItem>)

data class FeelingsListItem(
    val id: Int,
    val title: String,
    val position: Int,
    val image: String
)