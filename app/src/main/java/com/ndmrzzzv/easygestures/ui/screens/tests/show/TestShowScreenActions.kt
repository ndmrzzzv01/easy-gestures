package com.ndmrzzzv.easygestures.ui.screens.tests.show

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class TestShowScreenActions(
    val goToResultPage: () -> Unit
) {

    companion object {
        fun create(
            navController: NavHostController,
        ): TestShowScreenActions {
            return TestShowScreenActions(
                goToResultPage = {
                    navController.navigate(Screens.ResultsScreen.route)
                },
            )
        }
    }

}