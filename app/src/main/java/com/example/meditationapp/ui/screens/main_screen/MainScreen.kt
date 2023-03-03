package com.example.meditationapp.ui.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditationapp.R
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.QuoteModel
import com.example.meditationapp.models.Screen
import com.example.meditationapp.ui.theme.bgColor

@Composable
fun MainScreen(listFeelings: List<FeelingsListItem>, listQuotes: List<QuoteModel>) {
    val navController = rememberNavController()
    val bottomItems = listOf(Screen.Home, Screen.Sound, Screen.Profile)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = bgColor,
                contentPadding = PaddingValues(top = 40.dp, start = 25.dp, end = 25.dp),
                elevation = 0.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_btn),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.logo), contentDescription = null,
                        tint = Color.White
                    )
                    IconButton(onClick = { }) {
                        Image(
                            painter = painterResource(id = R.drawable.profile_pic),
                            contentDescription = null,
                            modifier = Modifier.size(45.dp)
                        )
                    }
                }

            }
        },
        bottomBar = {
            BottomNavigation(backgroundColor = bgColor) {
                bottomItems.forEach { screen ->
                    BottomNavigationItem(
                        selected = false,
                        onClick = {
                            navController.navigate(screen.route)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
            composable("home") { HomeScreen("Павел", listFeelings, listQuotes) }
            composable("sound") { SoundScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}