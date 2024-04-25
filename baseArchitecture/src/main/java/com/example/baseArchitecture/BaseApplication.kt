package com.example.baseArchitecture

import android.app.Application
import timber.log.Timber

abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}