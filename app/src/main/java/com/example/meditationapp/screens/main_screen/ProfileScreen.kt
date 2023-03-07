package com.example.meditationapp.screens.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.meditationapp.R
import com.example.meditationapp.models.user

@Composable
fun ProfileScreen() {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = RoundedCornerShape(150.dp)) {
            AsyncImage(
                model = user.avatar,
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        }
        Text(
            text = user.nickname,
            fontFamily = FontFamily(Font(R.font.alegreya_medium)),
            fontSize = 35.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

    }
}