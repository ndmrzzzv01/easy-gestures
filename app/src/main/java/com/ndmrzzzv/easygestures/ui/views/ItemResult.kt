package com.ndmrzzzv.easygestures.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import com.ndmrzzzv.easygestures.R

@Composable
fun ItemResult(
    question: String?,
    answer: String?,
    result: String?,
    colorOfBorder: Color,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                onClick()
            },
        border = BorderStroke(1.dp, colorOfBorder),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Question: $question",
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Your answer: $answer",
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Result: $result",
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
        }
    }
}