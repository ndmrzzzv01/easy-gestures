package com.ndmrzzzv.easygestures.ui.screens.myaccount

import android.net.Uri
import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.getImageForMyAccountLauncher
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class MyAccountActions(
    val updateInfoAboutUser: (uri: Uri?, name: String?) -> Unit,
    val changePhoto: (uri: Uri?) -> Unit,
    val findACourse: () -> Unit,
    val goToFavourite: () -> Unit,
    val signOut: () -> Unit
) {

    companion object {
        fun create(
            viewModel: MyAccountViewModel,
            navController: NavHostController
        ): MyAccountActions {
            return MyAccountActions(
                updateInfoAboutUser = { uri, name ->
                    viewModel.updateInfo(uri, name)
                },
                changePhoto = {
                    viewModel.changePhotoUser(it)
                },
                findACourse = {
                    navController.navigate(Screens.SearchScreen.route)
                },
                goToFavourite = {
                    navController.navigate(Screens.FavouriteScreen.route)
                },
                signOut = {
                    viewModel.signOut()
                    navController.navigate(Screens.LoginScreen.route)
                }
            )
        }
    }

}