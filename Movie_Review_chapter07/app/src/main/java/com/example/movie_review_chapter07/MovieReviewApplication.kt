package com.example.movie_review_chapter07

import android.app.Application
import com.example.movie_review_chapter07.di.appModule
import com.example.movie_review_chapter07.di.dataModule
import com.example.movie_review_chapter07.di.domainModule
import com.example.movie_review_chapter07.di.presenterModule
import com.example.movie_review_chapter07.utility.MovieDataGenerator
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieReviewApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) {
                    Level.DEBUG
                } else {
                    Level.NONE
                }
            )
            androidContext(this@MovieReviewApplication)
            modules(appModule + dataModule + domainModule + presenterModule)
        }

//        MovieDataGenerator().generate()
    }
}