package com.ndmrzzzv.easygestures.ui.screens.choose_test

import androidx.navigation.NavHostController
import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.easygestures.ui.screens.Screens
import com.ndmrzzzv.easygestures.utils.StudyData

data class ChooseTestScreenActions(
    val goToStudyLesson: (lesson: Lesson) -> Unit,
) {

    companion object {
        fun create(
            navController: NavHostController,
        ): ChooseTestScreenActions {
            return ChooseTestScreenActions(
               goToStudyLesson =  {
                   StudyData.lesson = it
                   navController.navigate(Screens.TestsScreen.route)
               }
            )
        }
    }

}