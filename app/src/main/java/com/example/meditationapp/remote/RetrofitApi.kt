package com.example.meditationapp.remote
import androidx.core.view.accessibility.AccessibilityEventCompat.ContentChangeType
import com.example.meditationapp.models.FeelingsListResponse
import com.example.meditationapp.models.NotAuthUser
import com.example.meditationapp.models.QuotesListResponse
import com.example.meditationapp.models.UserModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
    fun loginUser(@Body user: NotAuthUser): Observable<UserModel>

    companion object Factory{
        fun create():RetrofitApi{
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://mskko2021.mad.hakta.pro/api/")
                .build()

            return retrofit.create(RetrofitApi::class.java);
        }
    }

}