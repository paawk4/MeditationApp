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
import com.example.meditationapp.ui.screens.LoginScreen
import com.example.meditationapp.ui.screens.OnBoardingScreen
import com.example.meditationapp.ui.screens.main_screen.MainScreen

sealed class Screen(val route: String, val icon: Int?) {
    // Main screen navigation
    object Home : Screen("home", R.drawable.home)
    object Sound : Screen("sound", R.drawable.sound)
    object Profile : Screen("profile", R.drawable.profile)

    object Start : Screen("start", null)
    object Login : Screen("login", null)
    object Register : Screen("register", null)
    object Main : Screen("main", null)
}

@Composable
fun MeditationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "start",
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
        composable(Screen.Main.route) { MainScreen(listFeelings, listQuotes) }
    }
}