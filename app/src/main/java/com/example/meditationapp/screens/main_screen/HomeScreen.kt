package com.example.meditationapp.screens.main_screen

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
import com.example.meditationapp.models.QuotesListItem
import com.example.meditationapp.models.user
import com.example.meditationapp.screens.single_items.FeelingsItem
import com.example.meditationapp.screens.single_items.QuoteItem

@Composable
fun HomeScreen(
    listFeelings: List<FeelingsListItem>,
    listQuotes: List<QuotesListItem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp)
    ) {
        item {
            Text(
                text = "С возвращением, ${user.nickname}!",
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
                items(listFeelings) {
                    FeelingsItem(item = it)
                }
            }
        }
        if (listQuotes.isNotEmpty()) {
            items(listQuotes) {
                QuoteItem(item = it)
            }
        }
    }
}


