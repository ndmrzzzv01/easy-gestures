package com.ndmrzzzv.easygestures.ui.screens.myaccount

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class MyAccountActions(
    val updateInfoAboutUser: () -> Unit,
    val changePhoto: () -> Unit,
    val findACourse: () -> Unit,
    val goToFavourite: () -> Unit,
) {

    companion object {
        fun create(
            viewModel: MyAccountViewModel,
            navController: NavHostController
        ): MyAccountActions {
            return MyAccountActions(
                updateInfoAboutUser = {},
                changePhoto = {},
                findACourse = {
                    navController.navigate(Screens.SearchScreen.route)
                },
                goToFavourite = {
                    navController.navigate(Screens.FavouriteScreen.route)
                }
            )
        }
    }

}