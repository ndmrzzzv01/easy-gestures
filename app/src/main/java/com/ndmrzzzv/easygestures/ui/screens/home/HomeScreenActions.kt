package com.ndmrzzzv.easygestures.ui.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class HomeScreenActions(
    val goToMyAccountPage:() -> Unit,
    val goToFavouritePage:() -> Unit,
    val goToSearchPage:() -> Unit,
) {

    companion object {
        fun create(
            context: Context,
            navController: NavHostController
        ): HomeScreenActions {
            return HomeScreenActions(
                goToMyAccountPage = {
                    navController.navigate(Screens.MyAccountScreen.route)
                },
                goToFavouritePage = {
                    Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
                },
                goToSearchPage = {
                    navController.navigate(Screens.SearchScreen.route)
                }
            )
        }
    }

}