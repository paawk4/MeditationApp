package com.example.meditationapp.navigation

import com.example.meditationapp.R

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