package com.osamaaftab.gousto

import android.app.Application
import com.osamaaftab.gousto.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    AppModule,
                    ActivityModule,
                    UseCaseModule
                )
            )
        }
    }

    open fun getApiUrl(): String {
        return "https://api.gousto.co.uk/products/v2/"
    }
}

