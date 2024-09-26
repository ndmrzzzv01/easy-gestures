package com.ndmrzzzv.easygestures.ui.screens.tests

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.domain.network.data.Question
import com.ndmrzzzv.easygestures.R
import com.ndmrzzzv.easygestures.ui.screens.tests.show.TestShowScreen
import com.ndmrzzzv.easygestures.ui.screens.tests.write.TestWriteScreen

@Composable
fun TestsScreen(
    actions: TestsScreenActions,
    lesson: Lesson?,
    questions: List<Question>
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
            TestShowScreen(lesson, actions.goToResultPage)
        } else {
            TestWriteScreen(lesson, questions, actions.goToResultPage)
        }
    }

}