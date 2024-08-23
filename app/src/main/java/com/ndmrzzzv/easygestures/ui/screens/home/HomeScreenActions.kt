package com.ndmrzzzv.easygestures.ui.screens.home

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens

data class HomeScreenActions(
    val goToMyAccountPage:() -> Unit,
    val goToFavouritePage:() -> Unit,
    val goToSearchPage:() -> Unit,
) {

    companion object {
        fun create(
            navController: NavHostController
        ): HomeScreenActions {
            return HomeScreenActions(
                goToMyAccountPage = {
                    navController.navigate(Screens.MyAccountScreen.route)
                },
                goToFavouritePage = {
                    navController.navigate(Screens.FavouriteScreen.route)
                },
                goToSearchPage = {
                    navController.navigate(Screens.SearchScreen.route)
                }
            )
        }
    }

}