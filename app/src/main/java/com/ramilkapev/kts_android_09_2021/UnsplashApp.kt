package com.ramilkapev.kts_android_09_2021

import android.app.Application
import timber.log.Timber

class UnsplashApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}