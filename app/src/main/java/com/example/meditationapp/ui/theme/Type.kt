package com.example.meditationapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meditationapp.R

val fonts = FontFamily(
    Font(R.font.alegreyasans_regular),
    Font(R.font.alegreyasans_bold, weight = FontWeight.Bold),
    Font(R.font.alegreyasans_medium, weight = FontWeight.Medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 25.sp,
        color = Color.White
    )
//    caption = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    )

)