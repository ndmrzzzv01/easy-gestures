package com.ndmrzzzv.easygestures.ui.screens

sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash_screen")
    object LoginScreen: Screens("login_screen")
    object HomeScreen: Screens("home_screen")
    object SearchScreen: Screens("search_screen")
    object MyAccountScreen: Screens("my_account_screen")
}