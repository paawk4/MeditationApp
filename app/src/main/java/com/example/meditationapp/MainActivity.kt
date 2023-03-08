package com.example.meditationapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.QuotesListItem
import com.example.meditationapp.navigation.MeditationNavHost
import com.example.meditationapp.remote.RetrofitApi
import com.example.meditationapp.screens.APP_ACTIVITY
import com.example.meditationapp.screens.SplashScreen
import com.example.meditationapp.ui.theme.MeditationAppTheme
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var myCompositeDisposable: CompositeDisposable
    private lateinit var retrofitApi: RetrofitApi

    private var listFeelings = listOf<FeelingsListItem>()
    private var listQuotes = listOf<QuotesListItem>()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        APP_ACTIVITY = this
        myCompositeDisposable = CompositeDisposable()
        retrofitApi = RetrofitApi.create()

        setContent {
            SplashScreen()
            CoroutineScope(Dispatchers.IO).launch {
                listFeelings = retrofitApi.getFeelings().data
                listQuotes = retrofitApi.getQuotes().data

                runOnUiThread {
                    setContent {
                        MeditationAppTheme {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colors.background
                            ) {
                                MeditationNavHost(
                                    retrofitApi = retrofitApi,
                                    listFeelings = listFeelings,
                                    listQuotes = listQuotes,
                                    compositeDisposable = myCompositeDisposable
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable.clear()
    }
}
