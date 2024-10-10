package com.ndmrzzzv.easygestures.ui.screens.tests

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class TestsScreenActions(
    val showWriteTestScreen: () -> Unit,
    val showShowTestScreen: () -> Unit,
) {

    companion object {
        fun create(
            navController: NavHostController,
        ): TestsScreenActions {
            return TestsScreenActions(
                showWriteTestScreen = {
                    navController.navigate(Screens.WriteTestScreen.route)
                },
                showShowTestScreen = {
                    navController.navigate(Screens.ShowTestScreen.route)
                }
            )
        }
    }

}