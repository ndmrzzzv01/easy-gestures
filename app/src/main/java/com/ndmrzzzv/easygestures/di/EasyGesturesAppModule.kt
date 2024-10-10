package com.ndmrzzzv.easygestures.di

import com.ndmrzzzv.domain.db.usecase.AddFavouriteCourseToDbUseCase
import com.ndmrzzzv.domain.db.usecase.DeleteFavouriteCourseFromDbUseCase
import com.ndmrzzzv.domain.db.usecase.GetAllFavouriteCoursesUseCase
import com.ndmrzzzv.domain.network.usecase.GetAllCoursesUseCase
import com.ndmrzzzv.domain.network.usecase.GetAllLessonsUseCase
import com.ndmrzzzv.easygestures.ui.screens.choose_test.ChooseTestViewModel
import com.ndmrzzzv.easygestures.ui.screens.login.LoginViewModel
import com.ndmrzzzv.easygestures.ui.screens.myaccount.MyAccountViewModel
import com.ndmrzzzv.easygestures.ui.screens.result.ResultViewModel
import com.ndmrzzzv.easygestures.ui.screens.search.SearchViewModel
import com.ndmrzzzv.easygestures.ui.screens.tests.TestsViewModel
import com.ndmrzzzv.easygestures.ui.screens.tests.show.TestShowViewModel
import com.ndmrzzzv.easygestures.ui.screens.tests.write.TestWriteViewModel
import com.ndmrzzzv.easygestures.utils.ClassifyImage
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // view models
    viewModel { LoginViewModel(androidContext()) }

    viewModel { SearchViewModel(get()) }

    viewModel { ChooseTestViewModel(get()) }

    viewModel { MyAccountViewModel(androidContext()) }

    viewModel { TestsViewModel() }

    viewModel { TestWriteViewModel() }

    viewModel { TestShowViewModel() }

    viewModel { ResultViewModel() }

    // usecase
    single { GetAllCoursesUseCase(get()) }

    single { GetAllLessonsUseCase(get()) }

    single { GetAllFavouriteCoursesUseCase(get()) }

    single { DeleteFavouriteCourseFromDbUseCase(get()) }

    single { AddFavouriteCourseToDbUseCase(get()) }

    // other
    single { ClassifyImage(androidApplication()) }

}