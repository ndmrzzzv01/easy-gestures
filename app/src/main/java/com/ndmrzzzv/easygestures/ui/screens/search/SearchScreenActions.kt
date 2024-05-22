package com.ndmrzzzv.easygestures.ui.screens.search

import androidx.navigation.NavHostController

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