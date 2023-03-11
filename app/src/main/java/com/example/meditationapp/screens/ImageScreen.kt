package com.example.meditationapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meditationapp.navigation.MeditationNavHost
import com.example.meditationapp.room.dao.ImageDao
import com.example.meditationapp.room.entities.ImageEntity
import com.example.meditationapp.ui.theme.MeditationAppTheme
import com.example.meditationapp.utils.stringToBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ImageScreen(imageItem: ImageEntity, imageDao: ImageDao) {
    MeditationAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(Modifier.fillMaxSize()) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        bitmap = stringToBitmap(imageItem.image).asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            imageDao.deleteImage(imageItem)
                        }

                    }) {
                        Text(
                            text = "удалить",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    }
                    TextButton(onClick = { }) {
                        Text(
                            text = "закрыть",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}