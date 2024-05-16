package com.ndmrzzzv.easygestures.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.ndmrzzzv.easygestures.R

@Composable
fun LoginScreen(
    actions: LoginScreenActions,
    authState: AuthState,
    client: GoogleSignInClient,
) {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        alpha = 0.5f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            text = "Welcome!",
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFFC060B1),
            fontFamily = FontFamily.Cursive
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onClick = { actions.loginWithEmail(email, password) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC060B1))
        ) {
            Text("Login with Email", color = Color.White)
        }

        Divider(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            thickness = 1.dp,
            color = Color(0xFFC060B1)
        )

        val launcher = googleSignInLauncher(actions.loginWithGoogle)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { launcher.launch(client.signInIntent) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC060B1))
        ) {
            Text("Login with Google", color = Color.White)
        }

        when (authState) {
            is AuthState.Success -> {
                actions.goToHomePage()
            }

            is AuthState.Error -> {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Error: ${authState.message}",
                    color = Color.Red,
                )
            }

            AuthState.None -> {}
        }
    }
}