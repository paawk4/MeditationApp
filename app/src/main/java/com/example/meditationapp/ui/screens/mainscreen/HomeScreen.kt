package com.example.meditationapp.ui.screens.mainscreen

import android.telephony.mbms.MbmsDownloadReceiver
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.meditationapp.R
import com.example.meditationapp.models.FeelingsModel
import com.example.meditationapp.models.QuoteModel
import com.example.meditationapp.ui.theme.bgColor


@Composable
fun HomeScreen(name: String) {
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
                item {
                    FeelingsItem(
                        FeelingsModel(
                            1,
                            "Спокойным",
                            1,
                            "https://mskko2021.mad.hakta.pro//uploads//feeling//calm%20(4).png"
                        )
                    )
                }
                item {
                    FeelingsItem(
                        FeelingsModel(
                            1,
                            "Спокойным",
                            1,
                            "https://mskko2021.mad.hakta.pro//uploads//feeling//calm%20(4).png"
                        )
                    )
                }
                item {
                    FeelingsItem(
                        FeelingsModel(
                            1,
                            "Спокойным",
                            1,
                            "https://mskko2021.mad.hakta.pro//uploads//feeling//calm%20(4).png"
                        )
                    )
                }
                item {
                    FeelingsItem(
                        FeelingsModel(
                            1,
                            "Спокойным",
                            1,
                            "https://mskko2021.mad.hakta.pro//uploads//feeling//calm%20(4).png"
                        )
                    )
                }
                item {
                    FeelingsItem(
                        FeelingsModel(
                            1,
                            "Спокойным",
                            1,
                            "https://mskko2021.mad.hakta.pro//uploads//feeling//calm%20(4).png"
                        )
                    )
                }
            }
            QuoteItem(
                QuoteModel(
                    1,
                    "Заголовок блока",
                    "http://mskko2021.mad.hakta.pro//uploads//files//quote_1.png",
                    "Кратенькое описание блока с двумя строчками"
                )
            )
            QuoteItem(
                QuoteModel(
                    1,
                    "Заголовок блока",
                    "http://mskko2021.mad.hakta.pro//uploads//files//quote_1.png",
                    "Кратенькое описание блока с двумя строчками"
                )
            )
        }
    }
}

@Composable
fun QuoteItem(item: QuoteModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, end = 25.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .background(Color.Yellow)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)) {
                Image(
                    painter = painterResource(id = R.drawable.quote),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier.size(250.dp)
                )
            }
        }

    }
}

@Composable
fun FeelingsItem(item: FeelingsModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 25.dp)
    ) {
        Card(
            modifier = Modifier.size(75.dp),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.feeling),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(15.dp)
            )
//            AsyncImage(
//                model = item.image,
//                contentDescription = null,
//                alignment = Alignment.Center,
//                modifier = Modifier
//                    .padding(15.dp),
//                contentScale = ContentScale.FillBounds
//            )
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