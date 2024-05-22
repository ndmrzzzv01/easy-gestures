package com.ndmrzzzv.easygestures.ui.screens.splash

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

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
                    navController.navigate(Screens.LoginScreen.route)
                },
                navigateToHomeScreen = {
                    navController.navigate(Screens.HomeScreen.route)
                }
            )
        }
    }
}
