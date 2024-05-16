package com.ndmrzzzv.easygestures.ui.screens.login

import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class LoginScreenActions(
    val loginWithEmail: (email: String, password: String) -> Unit,
    val loginWithGoogle: (account: GoogleSignInAccount) -> Unit,
    val goToHomePage:() -> Unit
) {

    companion object {
        fun create(
            viewModel: LoginViewModel,
            navController: NavHostController
        ) : LoginScreenActions {
            return LoginScreenActions(
                loginWithEmail = { email, password ->
                    viewModel.signInWithEmailAndPassword(email, password)
                },
                loginWithGoogle = {
                    viewModel.handleGoogleSignInResult(it)
                },
                goToHomePage = {
                    navController.navigate("home_screen")
                }
            )
        }
    }
}
