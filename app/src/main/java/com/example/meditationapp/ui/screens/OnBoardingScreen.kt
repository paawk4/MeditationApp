package com.example.meditationapp.ui.screens

import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationapp.R
import com.example.meditationapp.ui.theme.fonts

@Composable
fun OnBoardingScreen() {
    Image(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.bg_image),
        contentDescription = "bg"
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(220.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )
        Text(
            text = "ПРИВЕТ",
            color = Color.White,
            fontSize = 34.sp,
            modifier = Modifier.padding(top = 30.dp),
            fontFamily = FontFamily(Font(R.font.alegreya_bold))
        )
        Text(
            color = Color.White,
            text = "Наслаждайся отборочными.\n" +
                    "Будь внимателен.\n" +
                    "Делай хорошо.",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )
        Button(
            onClick = {

            },
            modifier = Modifier
                .padding(top = 100.dp, start = 27.dp, end = 27.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Войти в аккаунт"
            )
        }
        TextButton(onClick = {

        }) {
            Text(
                text = "Еще нет аккаунта? Зарегистрируйтесь",
                fontSize = 20.sp,
                textAlign = TextAlign.Center, color = Color.White,
                lineHeight = 24.sp
            )
        }
    }
}