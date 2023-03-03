package com.example.meditationapp.ui.screens.main_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationapp.R
import com.example.meditationapp.models.FeelingsListItem
import com.example.meditationapp.models.QuoteModel
import com.example.meditationapp.ui.screens.single_items.FeelingsItem
import com.example.meditationapp.ui.screens.single_items.QuoteItem

@Composable
fun HomeScreen(name: String, listFeelings: List<FeelingsListItem>, listQuotes: List<QuoteModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, top = 30.dp)
    ) {
        item {
            Text(
                text = "С возвращением, $name!",
                fontFamily = FontFamily(Font(R.font.alegreya_medium)),
                fontSize = 34.sp,
                lineHeight = 40.sp
            )
            Text(
                text = "Каким ты себя ощущаешь сегодня?",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                lineHeight = 26.sp,
                modifier = Modifier.alpha(0.7f)
            )
            LazyRow(modifier = Modifier.padding(top = 25.dp)) {
                items(listFeelings){
                    FeelingsItem(item = it)
                }
            }
        }
        items(listQuotes){
            QuoteItem(item = it)
        }
    }
}


