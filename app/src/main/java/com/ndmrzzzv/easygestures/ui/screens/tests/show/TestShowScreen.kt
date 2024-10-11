package com.ndmrzzzv.easygestures.ui.screens.tests.show

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.domain.network.data.Question
import com.ndmrzzzv.easygestures.R
import com.ndmrzzzv.easygestures.ui.views.TestItem

@Composable
fun TestShowScreen(
    actions: TestShowScreenActions,
    lesson: Lesson?,
    imageUriMap: SnapshotStateMap<String, Uri>,
    currentQuestion: String?,
    currentUri: Uri?,
    shuffledQuestions: List<Question>,
) {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        alpha = 0.5f
    )

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

            val cameraLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { result ->
                    if (result && currentQuestion != null) {
                        currentUri?.let { uri ->
                            actions.saveUriWithQuestion(currentQuestion, uri)
                        }
                    }
                }

            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    currentUri?.let { uri ->
                        cameraLauncher.launch(uri)
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .weight(1f)
            ) {
                items(shuffledQuestions) { question ->
                    val imageUri = imageUriMap[question.text]
                    val image = if (imageUri != null && imageUri.path?.isNotEmpty() == true) {
                        rememberImagePainter(imageUri)
                    } else null

                    TestItem(
                        image = image,
                        question = question.text,
                        onClick = {
                            val uri = actions.createImageFile()

                            actions.updateCurrentUri(uri)
                            actions.updateCurrentQuestion(question.text)
                            
                            if (actions.checkPermission()) {
                                cameraLauncher.launch(uri)
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
                onClick = {
                    actions.goToResultPage()
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