package com.example.movie_review_chapter07.presentation.home

import com.example.movie_review_chapter07.domain.usecase.GetAllMoviesUseCase
import com.example.movie_review_chapter07.domain.usecase.GetRandomFeaturedMovieUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomePresenter(
    private val view: HomeContract.View,
    private val getRandomFeatureMovie: GetRandomFeaturedMovieUseCase,
    private val getAllMovies: GetAllMoviesUseCase
) : HomeContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        fetchMovies()
    }

    override fun onDestroyView() { }

    private fun fetchMovies() = scope.launch {
        try {
            view.showLoadingIndicator()
            val featureMovie = getRandomFeatureMovie()
            val movies = getAllMovies()
            view.showMovies(featureMovie, movies)
        } catch (e: Exception) {
            e.printStackTrace()
            view.showErrorDescription("에러가 발생했어요😢")
        } finally {
            view.hideLoadingIndicator()
        }
    }
}