package com.example.movie_review_chapter07.presentation.reviews

import com.example.movie_review_chapter07.domain.model.Movie
import com.example.movie_review_chapter07.domain.usecase.GetAllReviewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MovieReviewsPresenter(
    private val view: MovieReviewsContract.View,
    override val movie: Movie,
    private val getAllReviews: GetAllReviewsUseCase
) : MovieReviewsContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        view.showMovieInformation(movie)
        fetchReviews()
    }

    override fun onDestroyView() { }

    private fun fetchReviews() = scope.launch {
        try {
            view.showLoadingIndicator()
            view.showReviews(getAllReviews(movie.id!!))
        } catch (e: Exception) {
            e.printStackTrace()
            view.showErrorDescription("에러가 발생했어요😢")
        } finally {
            view.hideLoadingIndicator()
        }
    }
}