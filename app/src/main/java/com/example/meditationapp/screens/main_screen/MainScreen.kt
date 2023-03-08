package com.example.meditationapp.screens.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.meditationapp.R
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.QuotesListItem
import com.example.meditationapp.models.UserModel
import com.example.meditationapp.models.currentUser
import com.example.meditationapp.navigation.Screen
import com.example.meditationapp.room.dao.UserDao
import com.example.meditationapp.room.entities.UserEntity
import com.example.meditationapp.ui.theme.bgColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    navController: NavHostController,
    listFeelings: List<FeelingsListItem>,
    listQuotes: List<QuotesListItem>,
    userDao: UserDao
) {
    val bottomItems = listOf(Screen.Home, Screen.Sound, Screen.Profile)
    val mainNavController = rememberNavController()
    val currentDestination = remember { mutableStateOf(Screen.Home?.route) }

    CoroutineScope(Dispatchers.IO).launch {
        val user = userDao.getUser()
        if (user != null) {
            currentUser = UserModel(
                id = user.id,
                email = user.email,
                nickname = user.nickname,
                avatar = user.avatar,
                token = user.token
            )
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = bgColor,
                contentPadding = PaddingValues(
                    top = 10.dp,
                    start = 25.dp,
                    end = 25.dp,
                    bottom = 30.dp
                ),
                elevation = 0.dp
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val (menu, logo, profilePic) = createRefs()
                    IconButton(onClick = { }, modifier = Modifier.constrainAs(menu) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_btn),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.logo), contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.constrainAs(logo) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                    if (currentDestination.value == Screen.Profile.route) {
                        TextButton(
                            onClick = {
                                val user = UserEntity(
                                    id = currentUser.id,
                                    email = currentUser.email,
                                    nickname = currentUser.nickname,
                                    avatar = currentUser.avatar,
                                    token = currentUser.token
                                )
                                CoroutineScope(Dispatchers.IO).launch {
                                    userDao.deleteUser(user)
                                }
                                navController.navigate(Screen.Login.route)
                                navController.enableOnBackPressed(false)
                            },
                            modifier = Modifier.constrainAs(profilePic) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }) {
                            Text(
                                text = "exit",
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            mainNavController.navigate(Screen.Profile.route)
                            currentDestination.value = mainNavController.currentDestination?.route
                        },
                            modifier = Modifier.constrainAs(profilePic) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }) {
                            Card(shape = RoundedCornerShape(25.dp)) {
                                AsyncImage(
                                    model = currentUser.avatar,
                                    contentDescription = null,
                                    modifier = Modifier.size(45.dp)
                                )
                            }

                        }
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
                            mainNavController.navigate(screen.route)
                            currentDestination.value =
                                mainNavController.currentDestination?.route
                        },
                        icon = {
                            screen.icon?.let { painterResource(id = it) }?.let {
                                if (currentDestination.value == screen.route) {
                                    Icon(
                                        painter = it,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                } else {
                                    Icon(
                                        painter = it,
                                        contentDescription = null,
                                        tint = Color(0xFF959697)
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = mainNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        ) {
            composable(Screen.Home.route) {
                HomeScreen(listFeelings, listQuotes)
                currentDestination.value =
                    mainNavController.currentDestination?.route
            }
            composable(Screen.Sound.route) {
                SoundScreen()
                currentDestination.value =
                    mainNavController.currentDestination?.route
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
                currentDestination.value =
                    mainNavController.currentDestination?.route
            }
        }
    }
}