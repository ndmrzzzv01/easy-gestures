package com.ndmrzzzv.easygestures.ui.screens.choose_test

import androidx.navigation.NavHostController

data class ChooseTestScreenActions(
    val goToStudyLesson: () -> Unit,
) {

    companion object {
        fun create(
            navController: NavHostController,
        ): ChooseTestScreenActions {
            return ChooseTestScreenActions(
               goToStudyLesson =  {  }
            )
        }
    }

}