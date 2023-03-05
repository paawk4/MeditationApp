package com.example.meditationapp.ui.screens

import android.annotation.SuppressLint
import android.text.method.PasswordTransformationMethod
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import com.example.meditationapp.R
import com.example.meditationapp.models.NotAuthUser
import com.example.meditationapp.models.UserModel
import com.example.meditationapp.models.user
import com.example.meditationapp.remote.RetrofitApi
import com.example.meditationapp.ui.screens.main_screen.MainScreen
import com.example.meditationapp.ui.theme.bgColor
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import okhttp3.internal.wait



@Composable
fun LoginScreen(navController: NavHostController, retrofitApi: RetrofitApi) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp, top = 100.dp)
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
        var login by remember {
            mutableStateOf("")
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
                .padding(top = 100.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = bgColor,
                unfocusedIndicatorColor = Color.White
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
            keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Next),
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
            keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    login(login, password, navController, retrofitApi)
                }
            )
        )
        Button(
            onClick = { login(login, password, navController, retrofitApi) }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        ) {
            Text(text = "Sign in")
        }
        TextButton(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        ) {
            Text(text = "Register")
        }
    }
}

@SuppressLint("CheckResult")
fun login(
    login: String,
    password: String,
    navController: NavHostController,
    retrofitApi: RetrofitApi
) {
    if (login.isNotBlank() && password.isNotBlank()) {
        var isEmailTrue = false
        login.forEach {
            if (it == '@') {
                isEmailTrue = true
            }
        }
        if (isEmailTrue){
            retrofitApi.loginUser(NotAuthUser(login, password))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    user = result
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
        }
    }
}
