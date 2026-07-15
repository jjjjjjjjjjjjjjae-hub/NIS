package com.example.nis.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NisApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Қолданба іске қосылғанда орындалатын инициализациялар
        initializeApp()
    }

    private fun initializeApp() {
        // Мысалы:
        // Timber.plant(Timber.DebugTree())
        // FirebaseApp.initializeApp(this)
        // WorkManager.initialize(...)
    }
}
