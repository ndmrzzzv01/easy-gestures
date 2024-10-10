package com.ndmrzzzv.easygestures.ui.screens.tests

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class TestsScreenActions(
    val goToResultPage: () -> Unit,
    val saveUserAnswer: (pageOfQuestion: Int, answer: String) -> Unit,
    val calculateAndSaveResult: () -> Unit,
) {

    companion object {
        fun create(
            navController: NavHostController,
            viewModel: TestResultViewModel
        ): TestsScreenActions {
            return TestsScreenActions(
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