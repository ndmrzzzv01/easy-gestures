package com.ndmrzzzv.easygestures.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SplashScreen(
    actions: SplashScreenActions
) {
    LaunchedEffect(Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            actions.navigateToHomeScreen()
        } else {
            actions.navigateToLoginScreen()
        }
    }
}