package com.ndmrzzzv.easygestures.ui.screens.tests.write

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.domain.network.data.Question
import com.ndmrzzzv.easygestures.ui.views.PagerIndicator
import com.ndmrzzzv.easygestures.utils.StudyData
import com.ndmrzzzv.easygestures.ui.screens.tests.data.TestResult

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestWriteScreen(
    lesson: Lesson?,
    questions: List<Question>,
    goToResultPage: () -> Unit
) {
    if (lesson != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val pagerState = rememberPagerState(pageCount = { questions.size })

            val userAnswers = remember { mutableStateListOf(*Array(questions.size) { "" }) }

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
                        value = userAnswers[page],
                        onValueChange = { newAnswer ->
                            userAnswers[page] = newAnswer
                        },
                        label = { Text("Your answer") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
                            onClick = {
                                val results = questions.mapIndexed { index, question ->
                                    TestResult(
                                        question = "",
                                        correctAnswer = question.correct_answer ?: "",
                                        userAnswer = userAnswers[index]
                                    )
                                }
                                StudyData.result = results
                                goToResultPage()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF531549)
                            )
                        ) {
                            Text(text = "Go to see result")
                            Icon(
                                modifier = Modifier
                                    .padding(start = 4.dp),
                                imageVector = Icons.Outlined.ArrowForward,
                                contentDescription = "go"
                            )
                        }
                    }
                }
            }
        }
    }
}