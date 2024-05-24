package com.ndmrzzzv.easygestures.ui.screens.search

import android.util.Log
import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens
import com.ndmrzzzv.easygestures.utils.StudyData

data class SearchScreenActions(
    val goToCourseDetailPage: (courseId: Int?) -> Unit,
) {

    companion object {
        fun create(
            navController: NavHostController
        ): SearchScreenActions {
            return SearchScreenActions(
                goToCourseDetailPage = { id ->
                    StudyData.courseId = id
                    Log.d("TESTV - course id", StudyData.courseId.toString())
                    navController.navigate(Screens.ChooseTestScreen.route)
                }
            )
        }
    }

}