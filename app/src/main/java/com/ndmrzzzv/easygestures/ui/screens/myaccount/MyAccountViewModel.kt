package com.ndmrzzzv.easygestures.ui.screens.myaccount

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MyAccountViewModel: ViewModel() {

    val currentUser = FirebaseAuth.getInstance().currentUser

}