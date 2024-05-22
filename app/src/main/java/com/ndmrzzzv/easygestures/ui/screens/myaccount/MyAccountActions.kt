package com.ndmrzzzv.easygestures.ui.screens.myaccount

data class MyAccountActions(
    val updateInfoAboutUser: () -> Unit,
    val changePhoto: () -> Unit,
    val findACourse: () -> Unit,
    val goToFavourite: () -> Unit,
) {

    companion object {
        fun create(
            viewModel: MyAccountViewModel
        ): MyAccountActions {
            return MyAccountActions(
                updateInfoAboutUser = {},
                changePhoto = {},
                findACourse = {},
                goToFavourite = {}
            )
        }
    }

}