package com.ndmrzzzv.easygestures.ui.screens.tests.write

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class TestWriteScreenActions(
    val goToResultPage: () -> Unit,
    val saveUserAnswer: (pageOfQuestion: Int, answer: String) -> Unit,
    val calculateAndSaveResult: () -> Unit,
) {

    companion object {
        fun create(
            navController: NavHostController,
            viewModel: TestWriteViewModel
        ): TestWriteScreenActions {
            return TestWriteScreenActions(
                goToResultPage = {
                    navController.navigate(Screens.ResultsScreen.route)
                },
                saveUserAnswer = { pageOfQuestion, answer ->
                    viewModel.updateUserAnswer(pageOfQuestion, answer)
                },
                calculateAndSaveResult = {
                    viewModel.calculateAndSaveResults()
                }
            )
        }
    }

}