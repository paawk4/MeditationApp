package com.example.meditationapp.models

import com.example.meditationapp.R

sealed class Screen(val route: String, val icon: Int) {
    object Home : Screen("home", R.drawable.home)
    object Sound : Screen("sound", R.drawable.sound)
    object Profile : Screen("profile", R.drawable.profile)
}