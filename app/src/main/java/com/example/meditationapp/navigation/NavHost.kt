package com.example.meditationapp.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.QuotesListItem
import com.example.meditationapp.remote.RetrofitApi
import com.example.meditationapp.screens.LoginScreen
import com.example.meditationapp.screens.OnBoardingScreen
import com.example.meditationapp.screens.main_screen.MainScreen
import io.reactivex.disposables.CompositeDisposable

@Composable
fun MeditationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "start",
    retrofitApi: RetrofitApi,
    listFeelings: List<FeelingsListItem>,
    listQuotes: List<QuotesListItem>,
    compositeDisposable: CompositeDisposable
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Start.route) { OnBoardingScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController, retrofitApi, compositeDisposable) }
        composable(Screen.Register.route) { Text(text = "Регистрация") }
        composable(Screen.Main.route) { MainScreen(navController, listFeelings, listQuotes) }
    }
}