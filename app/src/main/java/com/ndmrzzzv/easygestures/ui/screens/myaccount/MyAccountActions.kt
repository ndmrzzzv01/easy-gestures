package com.ndmrzzzv.easygestures.ui.screens.myaccount

data class MyAccountActions(
    val updatePhoto: () -> Unit,
) {

    companion object {
        fun create(
            viewModel: MyAccountViewModel
        ): MyAccountActions {
            return MyAccountActions(
                updatePhoto = {},
            )
        }
    }

}