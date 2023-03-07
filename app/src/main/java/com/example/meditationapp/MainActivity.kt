package com.example.meditationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.FeelingsListResponse
import com.example.meditationapp.models.QuotesListItem
import com.example.meditationapp.models.QuotesListResponse
import com.example.meditationapp.navigation.MeditationNavHost
import com.example.meditationapp.remote.RetrofitApi
import com.example.meditationapp.screens.APP_ACTIVITY
import com.example.meditationapp.ui.theme.MeditationAppTheme
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : ComponentActivity() {

    private lateinit var retrofitApi: RetrofitApi
    private lateinit var myCompositeDisposable: CompositeDisposable

    private var listFeelings = listOf<FeelingsListItem>()
    private var listQuotes = listOf<QuotesListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        APP_ACTIVITY = this
        retrofitApi = RetrofitApi.create()
        myCompositeDisposable = CompositeDisposable()

        loadData()



        setContent {
            MeditationAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MeditationNavHost(
                        listFeelings = listFeelings,
                        listQuotes = listQuotes,
                        retrofitApi = retrofitApi,
                        compositeDisposable = myCompositeDisposable
                    )
                }
            }
        }
    }

    private fun loadData() {
        myCompositeDisposable.add(
            retrofitApi.getFeelings()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::feelingsResponse)
        )

        myCompositeDisposable.add(
            retrofitApi.getQuotes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::quotesResponse)
        )
    }
    private fun feelingsResponse(response: FeelingsListResponse) {
        listFeelings = response.data
    }
    private fun quotesResponse(response: QuotesListResponse) {
        listQuotes = response.data
    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable.clear()
    }
}
