package com.ndmrzzzv.easygestures.ui.screens.home

import androidx.navigation.NavHostController

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

                },
                goToFavouritePage = {

                },
                goToSearchPage = {

                }
            )
        }
    }

}