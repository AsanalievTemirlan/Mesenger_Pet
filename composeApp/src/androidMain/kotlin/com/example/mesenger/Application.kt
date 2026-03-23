package com.example.mesenger

import android.app.Application
import com.example.mesenger.di.appModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(appModule)
        }
    }
}
