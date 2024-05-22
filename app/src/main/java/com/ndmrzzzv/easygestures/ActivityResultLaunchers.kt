package com.ndmrzzzv.easygestures

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException

@Composable
fun googleSignInLauncher(loginWithGoogle: (account: GoogleSignInAccount) -> Unit) =
    rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                loginWithGoogle(account)
            }
        } catch (e: ApiException) {
            // Handle error
        }
    }

@Composable
fun getImageForMyAccountLauncher(getData: (Uri) -> Unit) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent(),
    onResult = { uri ->
        uri?.let { getData(it) }
    }
)