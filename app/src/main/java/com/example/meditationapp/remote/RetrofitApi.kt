package com.example.meditationapp.remote
import com.example.meditationapp.models.FeelingsListResponse
import com.example.meditationapp.models.QuotesListResponse
import retrofit2.http.GET

interface RetrofitApi {

    @GET("feelings")
    suspend fun getFeelings(): FeelingsListResponse

    @GET("quotes")
    suspend fun getQuotes(): QuotesListResponse

}