package com.ndmrzzzv.easygestures.ui.screens.splash

import androidx.navigation.NavHostController

data class SplashScreenActions(
    val navigateToLoginScreen: () -> Unit,
    val navigateToHomeScreen: () -> Unit,
) {

    companion object {
        fun create(
            navController: NavHostController
        ): SplashScreenActions {
            return SplashScreenActions(
                navigateToLoginScreen = {
                    navController.navigate("login_screen")
                },
                navigateToHomeScreen = {
                    navController.navigate("home_screen")
                }
            )
        }
    }
}
