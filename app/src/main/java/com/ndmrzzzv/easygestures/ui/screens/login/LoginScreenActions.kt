package com.ndmrzzzv.easygestures.ui.screens.login

import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class LoginScreenActions(
    val loginWithEmail: () -> Unit,
    val loginWithGoogle: (account: GoogleSignInAccount) -> Unit,
    val goToHomePage:() -> Unit,
    val updateEmail: (email: String) -> Unit,
    val updatePassword: (password: String) -> Unit,
) {

    companion object {
        fun create(
            viewModel: LoginViewModel,
            navController: NavHostController
        ) : LoginScreenActions {
            return LoginScreenActions(
                loginWithEmail = {
                    viewModel.signInWithEmailAndPassword()
                },
                loginWithGoogle = {
                    viewModel.handleGoogleSignInResult(it)
                },
                goToHomePage = {
                    navController.navigate(Screens.HomeScreen.route)
                },
                updateEmail = { email ->
                    viewModel.updateEmail(email)
                },
                updatePassword = { password ->
                    viewModel.updatePassword(password)
                }
            )
        }
    }
}
