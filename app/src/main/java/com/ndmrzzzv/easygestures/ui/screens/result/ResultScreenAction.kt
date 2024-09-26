package com.ndmrzzzv.easygestures.ui.screens.result

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

class ResultScreenAction(
    val goToHomePage: () -> Unit
) {

    companion object {
        fun create(
            navController: NavHostController
        ): ResultScreenAction {
            return ResultScreenAction(
                goToHomePage = {
                    navController.navigate(Screens.HomeScreen.route)
                }
            )
        }
    }

}