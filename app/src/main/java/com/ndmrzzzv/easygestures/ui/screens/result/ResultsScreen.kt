package com.ndmrzzzv.easygestures.ui.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ndmrzzzv.easygestures.R
import com.ndmrzzzv.easygestures.ui.views.ItemResult
import com.ndmrzzzv.easygestures.utils.StudyData

@Composable
fun ResultsScreen(actions: ResultScreenAction) {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        alpha = 0.5f
    )

    Column(
        modifier = Modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Center,
            text = "Your results",
            fontFamily = FontFamily.Cursive,
            fontSize = 24.sp
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 8.dp)
                .weight(1f)
        ) {
            items(StudyData.result) { (userAnswer, question) ->
                val isCorrect = question == userAnswer
                val colorOfBorder = if (isCorrect) Color.Green else Color.Red
                ItemResult(
                    question = "Question",
                    answer = userAnswer,
                    result = question,
                    colorOfBorder = colorOfBorder
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                .height(40.dp)
                .align(Alignment.End),
            onClick = {
                actions.goToHomePage()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF531549)
            )
        ) {
            Text(text = "Go to Home Screen")
            Icon(
                modifier = Modifier
                    .padding(start = 4.dp),
                imageVector = Icons.Outlined.Home,
                contentDescription = "go"
            )
        }
    }
}