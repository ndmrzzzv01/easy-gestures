package com.ndmrzzzv.easygestures.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemOfCourse() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable {},
        border = BorderStroke(1.dp, Color.Magenta),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .padding(24.dp),
                painter = painterResource(id = 0),
                contentDescription = ""
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 16.dp),
            ) {
                Text(
                    modifier = Modifier,
                    text = "pageItem.title",
                    fontSize = 22.sp,
                    fontFamily = FontFamily.Cursive
                )
                Text(
                    modifier = Modifier.padding(end = 16.dp, top = 8.dp),
                    text = "pageItem.description",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Serif,
                    lineHeight = 14.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_Item() {
    ItemOfCourse()
}