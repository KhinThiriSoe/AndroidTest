package com.khinthirisoe.lomotif

import android.app.Application
import com.khinthirisoe.lomotif.di.app
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@MainApplication)
            // modules
            modules(app)
        }
    }
}