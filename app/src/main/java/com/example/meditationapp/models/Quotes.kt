package com.example.meditationapp.models

data class QuotesListResponse(val data: List<QuotesListItem>)
data class QuotesListItem(
    val id: Int,
    val title: String,
    val image: String,
    val description: String
)