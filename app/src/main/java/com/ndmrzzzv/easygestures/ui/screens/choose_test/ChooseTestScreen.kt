package com.ndmrzzzv.easygestures.ui.screens.choose_test

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ndmrzzzv.easygestures.R
import com.ndmrzzzv.easygestures.ui.views.ItemOfCourse

@Composable
fun ChooseTestScreen(
    actions: ChooseTestScreenActions,
    state: LessonState
) {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        alpha = 0.5f
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is LessonState.Success -> {
                LazyColumn(
                    modifier = Modifier
                ) {
                    items(state.lessons) {
                        ItemOfCourse(
                            image = it.image,
                            title = "Type: ${it.type}",
                            description = it.description
                        )
                    }
                }
            }

            is LessonState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            is LessonState.Error -> {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = "Something went wrong. Exception: ${state.message}"
                )
            }

            is LessonState.None -> {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = "Sorry, there are no courses for this language yet :("
                )
            }
        }


    }

}