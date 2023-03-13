package com.example.meditationapp.screens.main_screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.Coil
import coil.compose.AsyncImage
import com.example.meditationapp.R
import com.example.meditationapp.models.currentUser
import com.example.meditationapp.navigation.Screen
import com.example.meditationapp.room.dao.ImageDao
import com.example.meditationapp.room.entities.ImageEntity
import com.example.meditationapp.screens.single_items.AddButton
import com.example.meditationapp.screens.single_items.ImageItem
import com.example.meditationapp.utils.bitmapToString
import com.example.meditationapp.utils.calculateCurrentTime
import com.example.meditationapp.utils.uriToBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

lateinit var launcher: ManagedActivityResultLauncher<String, Uri?>

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProfileScreen(imageDao: ImageDao, navController: NavHostController) {
    val listImages = remember { mutableStateOf(listOf<ImageEntity>()) }

    launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    imageDao.insertImage(
                        ImageEntity(
                            0,
                            bitmapToString(uriToBitmap(it)),
                            listImages.value.size.plus(1),
                            calculateCurrentTime()
                        )
                    )
                    listImages.value = imageDao.getAll()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    if (listImages.value.isEmpty()) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                listImages.value = imageDao.getAll()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(shape = RoundedCornerShape(150.dp)) {
            AsyncImage(
                model = currentUser.avatar,
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        }
        Text(
            text = currentUser.nickname,
            fontFamily = FontFamily(Font(R.font.alegreya_medium)),
            fontSize = 35.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 17.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            if (listImages.value.isNotEmpty()) {
                listImages.value.sortedBy { it.position }
                items(listImages.value) { imageItem ->
                    if (imageItem.image.isNotEmpty()) {
                        ImageItem(imageItem, Modifier.clickable {
                            navController.navigate("${Screen.Image.route}/${imageItem.id}")
                        })
                    }
                }
            }
            item {
                AddButton()
            }
        }
    }
}


