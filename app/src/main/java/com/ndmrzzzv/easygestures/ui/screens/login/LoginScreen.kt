package com.ndmrzzzv.easygestures.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun LoginScreen(
    actions: LoginScreenActions,
    authState: AuthState,
    client: GoogleSignInClient,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        Button(onClick = { actions.loginWithEmail(email, password) }) {
            Text("Login with Email")
        }
        val launcher = googleSignInLauncher(actions.loginWithGoogle)
        Button(onClick = { launcher.launch(client.signInIntent) }) {
            Text("Login with Google")
        }

        when (authState) {
            is AuthState.Success -> { actions.goToHomePage() }
            is AuthState.Error -> Text("Error: ${authState.message}")
            AuthState.None -> {}
        }
    }
}

