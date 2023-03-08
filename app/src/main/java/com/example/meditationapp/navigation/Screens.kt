package com.example.meditationapp.navigation

import com.example.meditationapp.R

sealed class Screen(val route: String, val icon: Int) {
    // Main screen navigation
    object Home : Screen("home", R.drawable.home)
    object Sound : Screen("sound", R.drawable.sound)
    object Profile : Screen("profile", R.drawable.profile)

    object Start : Screen("start", -1)
    object Login : Screen("login", -1)
    object Register : Screen("register", -1)
    object Main : Screen("main", -1)
}