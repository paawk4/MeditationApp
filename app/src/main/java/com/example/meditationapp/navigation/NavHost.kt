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
import com.example.meditationapp.room.dao.ImageDao
import com.example.meditationapp.room.dao.UserDao
import com.example.meditationapp.screens.LoginScreen
import com.example.meditationapp.screens.StartScreen
import com.example.meditationapp.screens.main_screen.MainScreen
import io.reactivex.disposables.CompositeDisposable

@Composable
fun MeditationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    retrofitApi: RetrofitApi,
    listFeelings: List<FeelingsListItem>,
    listQuotes: List<QuotesListItem>,
    compositeDisposable: CompositeDisposable,
    userDao: UserDao,
    imageDao: ImageDao
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Start.route) { StartScreen(navController) }
        composable(Screen.Login.route) {
            navController.enableOnBackPressed(false)
            LoginScreen(
                navController,
                retrofitApi,
                compositeDisposable,
                userDao
            )
        }
        composable(Screen.Register.route) {
            Text(text = "Регистрация")
            navController.enableOnBackPressed(true)
        }
        composable(Screen.Main.route) {
            MainScreen(
                navController,
                listFeelings,
                listQuotes,
                userDao,
                imageDao
            )
        }
    }
}