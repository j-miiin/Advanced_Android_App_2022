package com.example.shopping_app_part5_chapter02

import android.app.Application
import com.example.shopping_app_part5_chapter02.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ShoppingPart5Chapter02Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ShoppingPart5Chapter02Application)
            modules(appModule)
        }
    }
}