package com.example.meditationapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.example.meditationapp.navigation.MeditationNavHost
import com.example.meditationapp.remote.RetrofitApi
import com.example.meditationapp.screens.APP_ACTIVITY
import com.example.meditationapp.ui.theme.MeditationAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var retrofitApi: RetrofitApi

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        APP_ACTIVITY = this
        retrofitApi = RetrofitApi.create()



        CoroutineScope(Dispatchers.IO).launch {
            val listFeelings = retrofitApi.getFeelings().data
            val listQuotes = retrofitApi.getQuotes().data
            runOnUiThread {
                setContent {
                    MeditationAppTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            MeditationNavHost(
                                listFeelings = listFeelings,
                                listQuotes = listQuotes,
                                retrofitApi = retrofitApi
                            )
                        }
                    }
                }
            }
        }
    }
}
