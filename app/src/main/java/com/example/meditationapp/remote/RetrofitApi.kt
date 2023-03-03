package com.example.meditationapp.remote
import androidx.core.view.accessibility.AccessibilityEventCompat.ContentChangeType
import com.example.meditationapp.models.FeelingsListResponse
import com.example.meditationapp.models.NotAuthUser
import com.example.meditationapp.models.QuotesListResponse
import com.example.meditationapp.models.UserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitApi {
    @GET("feelings")
    suspend fun getFeelings(): FeelingsListResponse

    @GET("quotes")
    suspend fun getQuotes(): QuotesListResponse

    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun loginUser(@Body user: NotAuthUser): UserModel

}