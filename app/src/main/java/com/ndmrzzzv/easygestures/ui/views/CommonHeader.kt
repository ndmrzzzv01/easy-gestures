package com.ndmrzzzv.easygestures.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ndmrzzzv.easygestures.R

@Composable
fun CommonHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GifImage(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color(0xFF13B165),
            fontSize = 26.sp,
            fontFamily = FontFamily.Cursive
        )
    }
}

@Preview
@Composable
fun Preview() {
    CommonHeader()
}
