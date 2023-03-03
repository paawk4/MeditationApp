package com.example.meditationapp.ui.screens.single_items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.meditationapp.models.FeelingsListItem

@Composable
fun FeelingsItem(item: FeelingsListItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 20.dp)
    ) {
        Card(
            modifier = Modifier.size(85.dp),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(25.dp),
                contentScale = ContentScale.Fit
            )
        }
        Text(
            text = item.title,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 15.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
    }

}