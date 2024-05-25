package com.ndmrzzzv.easygestures.ui.screens.tests

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.easygestures.R
import com.ndmrzzzv.easygestures.ui.views.PagerIndicator

@Composable
fun TestsScreen(
    actions: TestsScreenActions,
    lesson: Lesson?
) {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        alpha = 0.5f
    )

    if (lesson != null) {

        if (lesson.type == "show") {
            TestShowScreen()
        } else {
            TestWriteScreen(lesson, actions.goToResultPage)
        }

    }

}

@Composable
fun TestShowScreen() {

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestWriteScreen(
    lesson: Lesson?,
    goToResultPage: () -> Unit
) {
    var isButtonVisible by remember { mutableStateOf(false) }
    val questions = lesson?.questions?.shuffled() ?: listOf()
    if (lesson != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val pagerState = rememberPagerState(pageCount = {
                questions.size
            })
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                text = lesson.global_question ?: "",
                fontFamily = FontFamily.Serif
            )
            PagerIndicator(pagerState = pagerState)
            HorizontalPager(state = pagerState) { page ->
                var answer by remember { mutableStateOf("") }
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val question = questions[page]
                    AsyncImage(
                        modifier = Modifier
                            .padding(top = 36.dp),
                        model = question.image,
                        contentDescription = ""
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                        value = answer,
                        onValueChange = { answer = it },
                        label = { Text("Your answer") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        trailingIcon = {
                            Icon(Icons.Outlined.Create, "answer")
                        }
                    )
                    val lastIndex = questions.lastIndex
                    if (lastIndex == page) {
                        Button(
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(end = 16.dp, top = 16.dp),
                            onClick = { goToResultPage() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF531549)
                            )
                        ) {
                            Text(text = "Go to see result")
                            Icon(Icons.Outlined.ArrowForward, "go")
                        }
                    }
                }
            }
        }
    }
}