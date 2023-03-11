package com.example.meditationapp.screens.single_items

import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meditationapp.room.dao.ImageDao
import com.example.meditationapp.room.entities.ImageEntity
import com.example.meditationapp.screens.APP_ACTIVITY
import com.example.meditationapp.screens.ImageScreen
import com.example.meditationapp.utils.stringToBitmap

@Composable
fun ImageItem(imageItem: ImageEntity, imageDao: ImageDao) {
    Card(shape = RoundedCornerShape(20.dp), modifier = Modifier
        .width(150.dp)
        .height(120.dp)
        .clickable {
//            APP_ACTIVITY.setContent {
//                ImageScreen(imageItem = imageItem, imageDao = imageDao)
//            }
        }) {
        Image(
            bitmap = stringToBitmap(imageItem.image).asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp, start = 17.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = imageItem.time, color = Color.White, fontWeight = FontWeight.Medium)
        }
    }
}

