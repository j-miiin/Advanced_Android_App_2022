package com.example.delivery_service_part5_chapter06.di

import android.app.Activity
import com.example.delivery_service_part5_chapter06.data.api.SweetTrackerApi
import com.example.delivery_service_part5_chapter06.data.api.Url
import com.example.delivery_service_part5_chapter06.data.db.AppDatabase
import com.example.delivery_service_part5_chapter06.data.preference.PreferenceManager
import com.example.delivery_service_part5_chapter06.data.preference.SharedPreferenceManager
import com.example.delivery_service_part5_chapter06.data.repository.*
import com.example.delivery_service_part5_chapter06.presentation.addtrackingitem.AddTrackingItemContract
import com.example.delivery_service_part5_chapter06.presentation.addtrackingitem.AddTrackingItemFragment
import com.example.delivery_service_part5_chapter06.presentation.addtrackingitem.AddTrackingItemPresenter
import com.example.delivery_service_part5_chapter06.presentation.trackingitems.TrackingItemsContract
import com.example.delivery_service_part5_chapter06.presentation.trackingitems.TrackingItemsFragment
import com.example.delivery_service_part5_chapter06.presentation.trackingitems.TrackingItemsPresenter
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    single { Dispatchers.IO }

    // Database
    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().trackingItemDao() }
    single { get<AppDatabase>().shippingCompanyDao() }

    // Api
    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }
    single<SweetTrackerApi> {
        Retrofit.Builder().baseUrl(Url.SWEET_TRACKER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }

    // Repository
    single<TrackingItemRepository> { TrackingItemRepositoryImpl(get(), get(), get()) }
//    single<TrackingItemRepository> { TrackingItemRepositoryStub() }
    single<ShippingCompanyRepository> { ShippingCompanyRepositoryImpl(get(), get(), get(), get()) }

    // Presentation
    scope<TrackingItemsFragment> {
        scoped<TrackingItemsContract.Presenter> { TrackingItemsPresenter(getSource()!!, get()) }
    }
    scope<AddTrackingItemFragment> {
        scoped<AddTrackingItemContract.Presenter> { AddTrackingItemPresenter(getSource()!!, get(), get()) }
    }
}