package com.example.meditationapp.models

data class QuotesListResponse(val data: List<QuoteModel>)

data class QuoteModel(
    val id: Int,
    val title: String,
    val image: String,
    val description: String
)