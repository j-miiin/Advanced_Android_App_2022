package com.example.shopping_app_part5_chapter02.di

import com.example.shopping_app_part5_chapter02.data.db.provideDB
import com.example.shopping_app_part5_chapter02.data.db.provideDao
import com.example.shopping_app_part5_chapter02.data.network.buildOkHttpClient
import com.example.shopping_app_part5_chapter02.data.network.provideGsonConverterFactory
import com.example.shopping_app_part5_chapter02.data.network.provideProductApiService
import com.example.shopping_app_part5_chapter02.data.network.provideProductRetrofit
import com.example.shopping_app_part5_chapter02.data.preference.PreferenceManager
import com.example.shopping_app_part5_chapter02.data.repository.DefaultProductRepository
import com.example.shopping_app_part5_chapter02.data.repository.ProductRepository
import com.example.shopping_app_part5_chapter02.domain.*
import com.example.shopping_app_part5_chapter02.domain.GetOrderedProductListUseCase
import com.example.shopping_app_part5_chapter02.domain.GetProductItemUseCase
import com.example.shopping_app_part5_chapter02.domain.GetProductListUseCase
import com.example.shopping_app_part5_chapter02.domain.OrderProductItemUseCase
import com.example.shopping_app_part5_chapter02.presentation.detail.ProductDetailViewModel
import com.example.shopping_app_part5_chapter02.presentation.list.ProductListViewModel
import com.example.shopping_app_part5_chapter02.presentation.main.MainViewModel
import com.example.shopping_app_part5_chapter02.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModel
    viewModel { MainViewModel() }
    viewModel { ProductListViewModel(get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { (productId: Long) -> ProductDetailViewModel(productId, get(), get()) }

    // Coroutine Dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // UseCase
    factory { GetProductItemUseCase(get()) }
    factory { GetProductListUseCase(get()) }
    factory { OrderProductItemUseCase(get()) }
    factory { GetOrderedProductListUseCase(get()) }
    factory { DeleteOrderedProductListUseCase(get()) }

    // Repository
    single<ProductRepository> { DefaultProductRepository(get(), get(), get()) }

    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideProductRetrofit(get(), get()) }

    single { provideProductApiService(get()) }

    single { PreferenceManager(androidContext()) }

    // Database
    single { provideDB(androidApplication()) }
    single { provideDao(get()) }
}