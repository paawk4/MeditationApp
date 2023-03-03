package com.example.meditationapp.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun retrofitInit(): RetrofitApi {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://mskko2021.mad.hakta.pro/api/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(RetrofitApi::class.java)
}