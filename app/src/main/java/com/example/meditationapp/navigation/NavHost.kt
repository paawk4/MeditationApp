package com.example.meditationapp.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditationapp.R
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.QuoteListItem
import com.example.meditationapp.remote.RetrofitApi
import com.example.meditationapp.screens.LoginScreen
import com.example.meditationapp.screens.OnBoardingScreen
import com.example.meditationapp.screens.main_screen.MainScreen



@Composable
fun MeditationNavHost(
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
        composable(Screen.Start.route) { OnBoardingScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController, retrofitApi) }
        composable(Screen.Register.route) { Text(text = "Регистрация") }
        composable(Screen.Main.route) { MainScreen(navController, listFeelings, listQuotes) }
    }
}