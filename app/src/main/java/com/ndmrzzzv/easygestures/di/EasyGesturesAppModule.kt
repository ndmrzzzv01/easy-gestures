package com.ndmrzzzv.easygestures.di

import com.ndmrzzzv.domain.db.usecase.AddFavouriteCourseToDbUseCase
import com.ndmrzzzv.domain.db.usecase.DeleteFavouriteCourseFromDbUseCase
import com.ndmrzzzv.domain.db.usecase.GetAllFavouriteCoursesUseCase
import com.ndmrzzzv.domain.network.usecase.GetAllCoursesUseCase
import com.ndmrzzzv.domain.network.usecase.GetAllLessonsUseCase
import com.ndmrzzzv.easygestures.ui.screens.choose_test.ChooseTestViewModel
import com.ndmrzzzv.easygestures.ui.screens.login.LoginViewModel
import com.ndmrzzzv.easygestures.ui.screens.myaccount.MyAccountViewModel
import com.ndmrzzzv.easygestures.ui.screens.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // view models
    viewModel { LoginViewModel(androidContext()) }

    viewModel { SearchViewModel(get()) }

    viewModel { ChooseTestViewModel(get()) }

    viewModel { MyAccountViewModel(androidContext()) }

    // usecase

    single { GetAllCoursesUseCase(get()) }

    single { GetAllLessonsUseCase(get()) }

    single { GetAllFavouriteCoursesUseCase(get()) }

    single { DeleteFavouriteCourseFromDbUseCase(get()) }

    single { AddFavouriteCourseToDbUseCase(get()) }


}