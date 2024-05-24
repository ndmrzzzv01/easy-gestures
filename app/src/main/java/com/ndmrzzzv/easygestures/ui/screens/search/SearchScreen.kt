package com.ndmrzzzv.easygestures.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ndmrzzzv.easygestures.R
import com.ndmrzzzv.easygestures.ui.views.ItemOfCourse

@Composable
fun SearchScreen(
    actions: SearchScreenActions,
    state: CourseState
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
        verticalArrangement = Arrangement.Top,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            value = "",
            onValueChange = { },
            label = { Text("Search") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            trailingIcon = {
                Icon(Icons.Outlined.Search, "search")
            }
        )

        when (state) {
            is CourseState.Success -> {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = "All results: ${state.courses.size} courses"
                )

                LazyColumn(
                    modifier = Modifier
                ) {
                    items(state.courses) {
                        ItemOfCourse(
                            image = it.image,
                            title = it.title,
                            description = it.description,
                            onClick = {
                                actions.goToCourseDetailPage(it.id)
                            }
                        ) 
                    }
                }
            }
            is CourseState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(top = 24.dp).align(Alignment.CenterHorizontally)
                )
            }
            is CourseState.Error -> {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = "Something went wrong. Exception: ${state.message}"
                )
            }
        }


    }

}

@Composable
@Preview
fun Preview_Search() {
    SearchScreen(actions = SearchScreenActions { }, state = CourseState.Loading)
}