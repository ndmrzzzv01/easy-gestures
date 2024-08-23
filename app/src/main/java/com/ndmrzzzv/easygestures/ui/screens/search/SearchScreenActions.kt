package com.ndmrzzzv.easygestures.ui.screens.search

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.Screens
import com.ndmrzzzv.easygestures.utils.StudyData

data class SearchScreenActions(
    val goToCourseDetailPage: (courseId: Int?) -> Unit,
    val searchEvent:(query: String) -> Unit
) {

    companion object {
        fun create(
            navController: NavHostController,
            viewModel: SearchViewModel
        ): SearchScreenActions {
            return SearchScreenActions(
                goToCourseDetailPage = { id ->
                    StudyData.courseId = id
                    navController.navigate(Screens.ChooseTestScreen.route)
                },
                searchEvent = {
                    viewModel.setSearchText(it)
                }
            )
        }
    }

}