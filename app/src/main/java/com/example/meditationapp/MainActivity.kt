package com.example.meditationapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.QuotesListItem
import com.example.meditationapp.navigation.MeditationNavHost
import com.example.meditationapp.navigation.Screen
import com.example.meditationapp.remote.RetrofitApi
import com.example.meditationapp.room.MeditationDatabase
import com.example.meditationapp.room.entities.FeelingEntity
import com.example.meditationapp.room.entities.QuoteEntity
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

        val db = Room.databaseBuilder(
            applicationContext,
            MeditationDatabase::class.java, "meditation-database"
        ).build()

        val feelingsDao = db.feelingsDao()
        val quotesDao = db.quotesDao()
        val userDao = db.userDao()

        setContent {
            SplashScreen()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    listFeelings = retrofitApi.getFeelings().data
                    listQuotes = retrofitApi.getQuotes().data
                } catch (_: Exception) {
                }
                if (listFeelings.isNotEmpty() && listQuotes.isNotEmpty()) {
                    feelingsDao.insertAll(listFeelings.map {
                        FeelingEntity(
                            id = it.id,
                            title = it.title,
                            position = it.position,
                            image = it.image
                        )
                    })
                    quotesDao.insertAll(listQuotes.map {
                        QuoteEntity(
                            id = it.id,
                            title = it.title,
                            image = it.image,
                            description = it.description
                        )
                    })
                }

                listFeelings = feelingsDao.getAll().map {
                    FeelingsListItem(
                        id = it.id,
                        title = it.title,
                        position = it.position,
                        image = it.image
                    )
                }
                listQuotes = quotesDao.getAll().map {
                    QuotesListItem(
                        id = it.id,
                        title = it.title,
                        image = it.image,
                        description = it.description
                    )
                }

                val user = userDao.getUser()
                var startDestination = Screen.Start.route
                if (user?.token != null){
                    startDestination = Screen.Main.route
                }
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
                                    compositeDisposable = myCompositeDisposable,
                                    userDao = userDao,
                                    startDestination = startDestination
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
