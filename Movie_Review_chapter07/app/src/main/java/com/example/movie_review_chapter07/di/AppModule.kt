package com.example.movie_review_chapter07.di

import com.example.movie_review_chapter07.data.api.MovieApi
import com.example.movie_review_chapter07.data.api.MovieFirestoreApi
import com.example.movie_review_chapter07.data.api.ReviewApi
import com.example.movie_review_chapter07.data.api.ReviewFirestoreApi
import com.example.movie_review_chapter07.data.repository.MovieRepository
import com.example.movie_review_chapter07.data.repository.MovieRepositoryImpl
import com.example.movie_review_chapter07.data.repository.ReviewRepository
import com.example.movie_review_chapter07.data.repository.ReviewRepositoryImpl
import com.example.movie_review_chapter07.domain.model.Movie
import com.example.movie_review_chapter07.domain.usecase.GetAllMoviesUseCase
import com.example.movie_review_chapter07.domain.usecase.GetAllReviewsUseCase
import com.example.movie_review_chapter07.domain.usecase.GetRandomFeaturedMovieUseCase
import com.example.movie_review_chapter07.presentation.home.HomeContract
import com.example.movie_review_chapter07.presentation.home.HomeFragment
import com.example.movie_review_chapter07.presentation.home.HomePresenter
import com.example.movie_review_chapter07.presentation.reviews.MovieReviewsFragment
import com.example.movie_review_chapter07.presentation.reviews.MovieReviewsContract
import com.example.movie_review_chapter07.presentation.reviews.MovieReviewsPresenter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule = module {
    single { Dispatchers.IO }
}

val dataModule = module {
    single { Firebase.firestore }

    single<MovieApi> { MovieFirestoreApi(get()) }
    single<ReviewApi> { ReviewFirestoreApi(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<ReviewRepository> { ReviewRepositoryImpl(get(), get()) }
}

val domainModule = module {
    factory { GetRandomFeaturedMovieUseCase(get(), get()) }
    factory { GetAllMoviesUseCase(get()) }
    factory { GetAllReviewsUseCase(get()) }
}

val presenterModule = module {
    scope<HomeFragment> {
        scoped<HomeContract.Presenter> { HomePresenter(getSource()!!, get(), get()) }
    }

    scope<MovieReviewsFragment> {
        scoped<MovieReviewsContract.Presenter> { (movie: Movie) -> MovieReviewsPresenter(getSource()!!, movie, get())}
    }
}