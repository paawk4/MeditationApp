package com.example.meditationapp.models

data class QuotesListResponse(val data: List<QuoteListItem>)

data class QuoteListItem(
    val id: Int,
    val title: String,
    val image: String,
    val description: String
)