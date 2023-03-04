package com.example.meditationapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.QuoteListItem
import com.example.meditationapp.remote.RetrofitApi
import com.example.meditationapp.ui.screens.APP_ACTIVITY
import com.example.meditationapp.ui.screens.LoginScreen
import com.example.meditationapp.ui.screens.OnBoardingScreen
import com.example.meditationapp.ui.screens.main_screen.MainScreen
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
            try {
                val listFeelings = retrofitApi.getFeelings().data
                val listQuotes = retrofitApi.getQuotes().data
                runOnUiThread {
                    setContent {
                        MeditationAppTheme {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colors.background
                            ) {
                                MyAppNavHost(
                                    listFeelings = listFeelings,
                                    listQuotes = listQuotes,
                                    retrofitApi = retrofitApi
                                )
                            }
                        }
                    }
                }

            } catch (e: Exception) {
                setContent {
                    MeditationAppTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            Text("Упс... Нет интернета")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "main",
    retrofitApi: RetrofitApi,
    listFeelings: List<FeelingsListItem>,
    listQuotes: List<QuoteListItem>
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("start") { OnBoardingScreen(navController) }
        composable("login") { LoginScreen(navController, retrofitApi) }
        composable("register") { Text(text = "Регистрация") }
        composable("main") { MainScreen(listFeelings, listQuotes) }
    }

}
