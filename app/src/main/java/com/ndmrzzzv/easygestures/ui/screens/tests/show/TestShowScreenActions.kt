package com.ndmrzzzv.easygestures.ui.screens.tests.show

import android.net.Uri
import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class TestShowScreenActions(
    val goToResultPage: () -> Unit,
    val updateCurrentQuestion: (question: String?) -> Unit,
    val updateCurrentUri: (uri: Uri) -> Unit,
    val saveUriWithQuestion: (currentQuestion: String, uri: Uri) -> Unit,
    val checkPermission: () -> Boolean,
    val createImageFile: () -> Uri
) {

    companion object {
        fun create(
            navController: NavHostController,
            viewModel: TestShowViewModel
        ): TestShowScreenActions {
            return TestShowScreenActions(
                goToResultPage = {
                    viewModel.classifyImageForAll {
                        navController.navigate(Screens.ResultsScreen.route)
                    }
                },
                updateCurrentQuestion = {
                    viewModel.updateCurrentQuestion(it)
                },
                updateCurrentUri = {
                    viewModel.updateCurrentUri(it)
                },
                saveUriWithQuestion = { currentQuestion, uri ->
                    viewModel.saveImageUri(currentQuestion, uri)
                },
                checkPermission = {
                    viewModel.checkCameraPermission()
                },
                createImageFile = {
                    viewModel.createImageUri()
                }
            )
        }
    }

}