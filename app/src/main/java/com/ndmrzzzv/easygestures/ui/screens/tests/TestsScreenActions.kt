package com.ndmrzzzv.easygestures.ui.screens.tests

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class TestsScreenActions(
    val goToResultPage: () -> Unit
) {

    companion object {
        fun create(
            navController: NavHostController
        ): TestsScreenActions {
            return TestsScreenActions(
                goToResultPage = {
                    navController.navigate(Screens.ResultsScreen.route)
                }
            )
        }
    }

}