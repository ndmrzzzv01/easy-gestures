package com.ndmrzzzv.easygestures.ui.screens.tests

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.easygestures.R
import com.ndmrzzzv.easygestures.ui.views.PagerIndicator
import com.ndmrzzzv.easygestures.ui.views.TestItem
import com.ndmrzzzv.network.utils.createImageFile
import java.util.Objects

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
            TestShowScreen(lesson, actions.goToResultPage)
        } else {
            TestWriteScreen(lesson, actions.goToResultPage)
        }

    }

}

@Composable
fun TestShowScreen(
    lesson: Lesson?,
    goToResultPage: () -> Unit
) {
    val context = LocalContext.current
    val imageUriMap = remember { mutableStateMapOf<String, Uri>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (lesson != null) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                text = lesson.description ?: "",
                fontFamily = FontFamily.Serif,
                lineHeight = 14.sp
            )

            var currentQuestion by remember { mutableStateOf<String?>(null) }
            var currentUri by remember { mutableStateOf<Uri?>(null) }

            val cameraLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { result ->
                    if (result && currentQuestion != null) {
                        currentUri?.let { uri ->
                            imageUriMap[currentQuestion!!] = uri
                        }
                    }
                }

            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                    currentUri?.let { uri ->
                        cameraLauncher.launch(uri)
                    }
                } else {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .weight(1f)
            ) {
                val questions = lesson.questions?.shuffled() ?: listOf()
                items(questions) { question ->
                    val imageUri = imageUriMap[question.text]
                    val image = if (imageUri != null && imageUri.path?.isNotEmpty() == true) {
                        rememberImagePainter(imageUri)
                    } else null

                    TestItem(
                        image = image,
                        question = question.text,
                        onClick = {
                            currentQuestion = question.text
                            val file = context.createImageFile()
                            currentUri = FileProvider.getUriForFile(
                                context, "com.ndmrzzzv.easygestures.provider", file
                            )
                            val permissionCheckResult = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                cameraLauncher.launch(currentUri!!)
                            } else {
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }
                    )
                }
            }
            Button(
                modifier = Modifier
                    .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .height(40.dp)
                    .align(Alignment.End),
                onClick = { goToResultPage() },
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestWriteScreen(
    lesson: Lesson?,
    goToResultPage: () -> Unit
) {
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