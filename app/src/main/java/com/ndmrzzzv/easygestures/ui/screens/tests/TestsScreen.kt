package com.ndmrzzzv.easygestures.ui.screens.tests

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.easygestures.R

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
            actions.showShowTestScreen()
        } else {
            actions.showWriteTestScreen()
        }
    }

}