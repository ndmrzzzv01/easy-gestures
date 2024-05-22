package com.ndmrzzzv.easygestures.ui.screens.myaccount

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyAccountViewModel(
    private val context: Context
): ViewModel() {

    private val _userImage = MutableStateFlow<Uri?>(null)
    val userImage: StateFlow<Uri?> = _userImage

    val currentUser = FirebaseAuth.getInstance().currentUser

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }

    fun updateInfo(uri: Uri?, name: String?) {
        val userUpdateInfo = UserProfileChangeRequest.Builder().apply {
            if (uri != null) photoUri = uri
            if (name != null) displayName = name
        }.build()
        FirebaseAuth.getInstance().currentUser?.updateProfile(userUpdateInfo)
        Toast.makeText(context, "Info about user updated", Toast.LENGTH_SHORT).show()
    }

    fun changePhotoUser(uri: Uri?) {
        _userImage.value = uri
    }

}