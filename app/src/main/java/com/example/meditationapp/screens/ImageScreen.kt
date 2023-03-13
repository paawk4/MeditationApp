package com.example.meditationapp.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.meditationapp.room.dao.ImageDao
import com.example.meditationapp.room.entities.ImageEntity
import com.example.meditationapp.utils.stringToBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ImageScreen(imageItemId: Int?, imageDao: ImageDao, navController: NavController) {
    val image = remember { mutableStateOf<ImageEntity?>(null) }

    imageItemId?.let {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                image.value = imageDao.getImageById(it)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    ConstraintLayout(
        Modifier.fillMaxSize()
    ) {
        val (column, row) = createRefs()
        Column(
            Modifier
                .fillMaxSize()
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    absoluteRight.linkTo(parent.absoluteRight)
                    absoluteLeft.linkTo(parent.absoluteLeft)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            image.value?.let {
                ZoomableImage(stringToBitmap(it.image))
//                Image(
//                    bitmap = stringToBitmap(it.image).asImageBitmap(),
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.FillWidth
//                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 65.dp, end = 65.dp)
                .constrainAs(row) {
                    bottom.linkTo(parent.bottom)
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = {
                imageItemId?.let {
                    CoroutineScope(Dispatchers.IO).launch {
                        imageDao.deleteImageById(it)
                    }
                    navController.popBackStack()
                }
            }) {
                Text(
                    text = "удалить",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
            TextButton(onClick = { navController.popBackStack() }) {
                Text(
                    text = "закрыть",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun ZoomableImage(image: Bitmap) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown()
                        do {
                            val event = awaitPointerEvent()
                            scale *= event.calculateZoom()
                            val offset = event.calculatePan()
                            offsetX += offset.x
                            offsetY += offset.y
                        } while (event.changes.any { it.pressed })
                    }
                }
            }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
                .fillMaxSize(),
            contentDescription = null,
            bitmap = image.asImageBitmap(),
            contentScale = ContentScale.FillWidth
        )
    }
}

