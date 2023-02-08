package com.kudagonish.languagedictionary

import android.app.Application
import com.kudagonish.languagedictionary.di.application
import com.kudagonish.languagedictionary.di.historyScreen
import com.kudagonish.languagedictionary.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class TranslatorApp : Application() {



    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(applicationContext)
            loadKoinModules(listOf(application, mainScreen, historyScreen)) }
    }
}