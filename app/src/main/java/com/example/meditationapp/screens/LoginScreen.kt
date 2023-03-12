package com.example.meditationapp.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meditationapp.R
import com.example.meditationapp.models.NotAuthUser
import com.example.meditationapp.models.UserModel
import com.example.meditationapp.models.currentUser
import com.example.meditationapp.remote.RetrofitApi
import com.example.meditationapp.room.dao.UserDao
import com.example.meditationapp.room.entities.UserEntity
import com.example.meditationapp.ui.theme.bgColor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*


@Composable
fun LoginScreen(
    navController: NavHostController,
    retrofitApi: RetrofitApi,
    compositeDisposable: CompositeDisposable,
    userDao: UserDao
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 250.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = "Sign in",
                fontFamily = FontFamily(Font(R.font.alegreya_medium)),
                fontSize = 34.sp,
                modifier = Modifier.padding(top = 30.dp)
            )
        }

        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            var login by remember {
                mutableStateOf(currentUser.email)
            }
            var password by remember {
                mutableStateOf("")
            }
            val focusManager = LocalFocusManager.current
            TextField(
                value = login,
                onValueChange = { login = it },
                label = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = bgColor,
                    unfocusedIndicatorColor = Color.White
                ),
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Пароль") },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = bgColor,
                    unfocusedIndicatorColor = Color.White
                ),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        login(
                            login,
                            password,
                            navController,
                            retrofitApi,
                            compositeDisposable,
                            userDao
                        )
                    }
                )
            )
            Button(
                onClick = {
                    login(
                        login,
                        password,
                        navController,
                        retrofitApi,
                        compositeDisposable,
                        userDao
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
            ) {
                Text(text = "Sign in")
            }
            TextButton(
                onClick = {
                    navController.navigate("register")

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
            ) {
                Text(text = "Register")
            }
        }
    }
}

@SuppressLint("CheckResult")
fun login(
    login: String,
    password: String,
    navController: NavHostController,
    retrofitApi: RetrofitApi,
    compositeDisposable: CompositeDisposable,
    userDao: UserDao
) {
    if (login.isNotBlank() && password.isNotBlank()) {
        var isEmailTrue = false
        login.forEach {
            if (it == '@') {
                isEmailTrue = true
            }
        }
        if (isEmailTrue) {
            compositeDisposable.add(
                retrofitApi.loginUser(NotAuthUser(login, password))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        CoroutineScope(Dispatchers.IO).launch {
                            userDao.insertUser(
                                UserEntity(
                                    id = result.id,
                                    email = result.email,
                                    nickname = result.nickname,
                                    avatar = result.avatar,
                                    token = result.token
                                )
                            )
                        }
                        navController.navigate("main")
                        navController.enableOnBackPressed(false)
                    }, { error ->
                        error.printStackTrace()
                        Toast.makeText(
                            APP_ACTIVITY,
                            "Введен неверный логин или пароль",
                            Toast.LENGTH_SHORT
                        ).show()
                    })
            )
        }
    }
}
