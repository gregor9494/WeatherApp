package com.example.grzesiek.myapplication.app

import android.app.Application
import com.example.grzesiek.myapplication.BuildConfig
import com.example.grzesiek.myapplication.di.koin.KoinModules
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        this.initializeLeakDetection()
        this.initInjection()
    }

    private fun initInjection() = startKoin {
        androidContext(this@AndroidApplication)
        modules(KoinModules.modules)
    }

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}
