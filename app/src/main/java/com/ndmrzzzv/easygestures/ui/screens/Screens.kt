package com.ndmrzzzv.easygestures.ui.screens

sealed class Screens(val route: String) {

    object SplashScreen: Screens("splash_screen")
    object LoginScreen: Screens("login_screen")
    object HomeScreen: Screens("home_screen")
    object SearchScreen: Screens("search_screen")
    object ChooseTestScreen: Screens("choose_test")

    object TestsScreen: Screens("tests")
    object WriteTestScreen: Screens("write_tests")
    object ShowTestScreen: Screens("show_tests")

    object ResultsScreen: Screens("results")
    object MyAccountScreen: Screens("my_account_screen")

}