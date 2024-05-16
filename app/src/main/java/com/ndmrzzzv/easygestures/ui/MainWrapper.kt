package com.ndmrzzzv.easygestures.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ndmrzzzv.easygestures.ui.screens.Screens
import com.ndmrzzzv.easygestures.ui.screens.home.HomeScreen
import com.ndmrzzzv.easygestures.ui.screens.login.LoginScreen
import com.ndmrzzzv.easygestures.ui.screens.login.LoginScreenActions
import com.ndmrzzzv.easygestures.ui.screens.login.LoginViewModel
import com.ndmrzzzv.easygestures.ui.screens.splash.SplashScreen
import com.ndmrzzzv.easygestures.ui.screens.splash.SplashScreenActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun EasyGesturesApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            val actions = SplashScreenActions.create(navController)
            SplashScreen(actions)
        }
        composable(Screens.LoginScreen.route) {
            val viewModel = koinViewModel<LoginViewModel>()
            val actions = LoginScreenActions.create(viewModel, navController)
            val authState = viewModel.authState.collectAsState().value
            val client = viewModel.getGoogleSignInClient()
            LoginScreen(actions, authState, client)
        }
        composable(Screens.HomeScreen.route) {
            HomeScreen()
        }
    }
}