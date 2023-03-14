package com.example.meditationapp.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.meditationapp.R
import com.example.meditationapp.room.dao.ImageDao
import com.example.meditationapp.room.entities.ImageEntity
import com.example.meditationapp.ui.theme.bgColor
import com.example.meditationapp.utils.deleteImageById
import com.example.meditationapp.utils.stringToBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


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
                CustomImage(it.id, stringToBitmap(it.image), imageDao, navController)
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
                    deleteImageById(it, imageDao)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomImage(imageId: Int, image: Bitmap, imageDao: ImageDao, navController: NavController) {
    val delete = SwipeAction(
        icon = rememberVectorPainter(ImageVector.vectorResource(id = R.drawable.ic_delete)),
        background = bgColor,
        isUndo = true,
        onSwipe = {
            deleteImageById(imageId, imageDao)
            navController.popBackStack()
        }
    )

    val back = SwipeAction(
        icon = rememberVectorPainter(ImageVector.vectorResource(id = R.drawable.ic_back)),
        background = bgColor,
        isUndo = true,
        onSwipe = { navController.popBackStack() },
    )

    ZoomableImage(
        modifier = Modifier
            .fillMaxSize(),
        isRotation = false,
        bitmap = image,
        back = back,
        delete = delete
    )
}


