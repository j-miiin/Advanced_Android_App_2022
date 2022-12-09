package com.example.shopping_app_part5_chapter02.di

import com.example.shopping_app_part5_chapter02.data.network.buildOkHttpClient
import com.example.shopping_app_part5_chapter02.data.network.provideGsonConverterFactory
import com.example.shopping_app_part5_chapter02.data.network.provideProductApiService
import com.example.shopping_app_part5_chapter02.data.network.provideProductRetrofit
import com.example.shopping_app_part5_chapter02.data.repository.DefaultProductRepository
import com.example.shopping_app_part5_chapter02.data.repository.ProductRepository
import com.example.shopping_app_part5_chapter02.domain.GetProductItemUseCase
import com.example.shopping_app_part5_chapter02.domain.GetProductListUseCase
import com.example.shopping_app_part5_chapter02.presentation.list.ProductListViewModel
import com.example.shopping_app_part5_chapter02.presentation.main.MainViewModel
import com.example.shopping_app_part5_chapter02.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModel
    viewModel { MainViewModel() }
    viewModel { ProductListViewModel(get()) }
    viewModel { ProfileViewModel() }

    // Coroutine Dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // UseCase
    factory { GetProductItemUseCase(get()) }
    factory { GetProductListUseCase(get()) }

    // Repository
    single<ProductRepository> { DefaultProductRepository(get(), get()) }

    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideProductRetrofit(get(), get()) }

    single { provideProductApiService(get()) }
}