package com.ndmrzzzv.easygestures.ui.screens.splash

import androidx.navigation.NavHostController
import com.ndmrzzzv.easygestures.ui.screens.search.SearchViewModel

data class SearchScreenActions(
    val goToCourseDetailPage: () -> Unit,
) {

    companion object {
        fun create(
            navController: NavHostController,
            viewModel: SearchViewModel
        ): SearchScreenActions {
            return SearchScreenActions(
                goToCourseDetailPage = {

                }
            )
        }
    }

}