package com.ndmrzzzv.easygestures.data

import com.ndmrzzzv.easygestures.R

data class HomeData(
    val id: Int,
    val image: Int,
    val title: String,
    val description: String,
    val type: HomeTypeScreen
)

enum class HomeTypeScreen {
    MY_ACCOUNT, FAVOURITE, SEARCH
}

object HomeList {
    fun get(): List<HomeData> {
        return listOf(
            HomeData(
                0,
                R.drawable.my_account_page,
                "My Account Page",
                "Provides easy access to important information and features associated with your user profile",
                HomeTypeScreen.MY_ACCOUNT
            ),
            HomeData(
                1,
                R.drawable.favourite_page,
                "Favourite Page",
                "Makes it easy to follow the courses you like and return to them at any time.",
                HomeTypeScreen.FAVOURITE
            ),
            HomeData(
                2,
                R.drawable.search_page,
                "Search Page",
                "Сan quickly find the courses or information they need by entering keywords into the search bar.",
                HomeTypeScreen.SEARCH
            ),
        )
    }
}
