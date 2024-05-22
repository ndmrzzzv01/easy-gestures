package com.ndmrzzzv.easygestures.di

import com.ndmrzzzv.easygestures.ui.screens.login.LoginViewModel
import com.ndmrzzzv.easygestures.ui.screens.myaccount.MyAccountViewModel
import com.ndmrzzzv.easygestures.ui.screens.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // view models
    viewModel { LoginViewModel(androidContext()) }

    viewModel { SearchViewModel() }

    viewModel { MyAccountViewModel() }


}