package com.ndmrzzzv.easygestures

import android.app.Application
import android.content.Context
import com.ndmrzzzv.easygestures.di.appModule
import com.ndmrzzzv.network.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EasyGesturesApp : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin {
            androidContext(this@EasyGesturesApp)
            modules(listOf(appModule, dataModule))
        }
    }

}