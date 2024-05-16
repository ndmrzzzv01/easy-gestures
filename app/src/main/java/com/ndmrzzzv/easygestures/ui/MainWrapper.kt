package com.ndmrzzzv.easygestures.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ndmrzzzv.easygestures.ui.screens.login.LoginScreen
import com.ndmrzzzv.easygestures.ui.screens.login.LoginScreenActions
import com.ndmrzzzv.easygestures.ui.screens.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EasyGesturesApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("splash_screen") {

        }
        composable("login_screen") {
            val viewModel = koinViewModel<LoginViewModel>()
            val actions = LoginScreenActions.create(viewModel, navController)
            val authState = viewModel.authState.collectAsState().value
            val client = viewModel.getGoogleSignInClient()
            LoginScreen(actions, authState, client)
        }
    }
}