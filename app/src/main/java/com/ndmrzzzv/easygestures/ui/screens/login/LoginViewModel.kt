package com.ndmrzzzv.easygestures.ui.screens.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.ndmrzzzv.easygestures.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    context: Context
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.None)
    val authState: StateFlow<AuthState> = _authState

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    private val client = GoogleSignIn.getClient(context, gso)

    fun signInWithEmailAndPassword() {
        val email = _email.value
        val password = _password.value
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _authState.value = if (task.isSuccessful) {
                    AuthState.Success
                } else {
                    AuthState.Error(task.exception?.message ?: "Unknown error")
                }
            }
    }

    fun handleGoogleSignInResult(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                _authState.value = if (task.isSuccessful) {
                    AuthState.Success
                } else {
                    AuthState.Error(task.exception?.message ?: "Unknown error")
                }
            }
    }

    fun getGoogleSignInClient() = client

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

}

sealed class AuthState {
    object None : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}
