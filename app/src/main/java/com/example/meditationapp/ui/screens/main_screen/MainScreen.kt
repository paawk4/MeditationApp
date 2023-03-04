package com.example.meditationapp.ui.screens.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.meditationapp.R
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.QuoteListItem
import com.example.meditationapp.navigation.MeditationNavHost
import com.example.meditationapp.navigation.Screen
import com.example.meditationapp.ui.screens.user
import com.example.meditationapp.ui.theme.bgColor

@Composable
fun MainScreen(
    listFeelings: List<FeelingsListItem>,
    listQuotes: List<QuoteListItem>
) {
    val bottomItems = listOf(Screen.Home, Screen.Sound, Screen.Profile)
    val navController = rememberNavController()
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
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        AsyncImage(
                            model = user.avatar,
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
                            screen.icon?.let { painterResource(id = it) }?.let {
                                Icon(
                                    painter = it,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        ) {
            composable(Screen.Home.route) { HomeScreen(listFeelings, listQuotes) }
            composable(Screen.Sound.route) { SoundScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}