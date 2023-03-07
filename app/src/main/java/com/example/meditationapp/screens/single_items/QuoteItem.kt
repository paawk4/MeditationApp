package com.example.meditationapp.screens.single_items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.meditationapp.R
import com.example.meditationapp.models.QuotesListItem
import com.example.meditationapp.ui.theme.bgColor

@Composable
fun QuoteItem(item: QuotesListItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, end = 25.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(contentAlignment = Alignment.CenterStart) {
            Column(
                modifier = Modifier
                    .padding(25.dp)
            ) {
                Text(
                    text = item.title,
                    color = bgColor,
                    fontFamily = FontFamily(Font(R.font.alegreya_medium)),
                    fontSize = 29.sp
                )
                Text(
                    text = item.description,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 19.sp,
                    modifier = Modifier.requiredWidth(200.dp),
                    lineHeight = 18.sp
                )
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(backgroundColor = bgColor),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    Text(
                        text = "подробнее",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(
                            start = 25.dp,
                            end = 25.dp,
                            top = 3.dp,
                            bottom = 3.dp
                        )
                    )
                }
            }
            AsyncImage(
                model = item.image,
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.size(190.dp).offset(x = 185.dp)
            )
        }

    }
}