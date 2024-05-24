package com.ndmrzzzv.easygestures

import android.app.Application
import com.ndmrzzzv.easygestures.di.appModule
import com.ndmrzzzv.network.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EasyGesturesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EasyGesturesApp)
            modules(listOf(appModule, dataModule))
        }
    }

}