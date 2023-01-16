package com.example.movie_review_chapter07.presentation.reviews

import com.example.movie_review_chapter07.domain.model.Movie
import com.example.movie_review_chapter07.domain.model.MovieReviews
import com.example.movie_review_chapter07.domain.model.Review
import com.example.movie_review_chapter07.presentation.BasePresenter
import com.example.movie_review_chapter07.presentation.BaseView

interface MovieReviewsContract {

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showMovieInformation(movie: Movie)

        fun showReviews(reviews: MovieReviews)

        fun showErrorToast(message: String)
    }

    interface Presenter : BasePresenter {
        val movie: Movie

        fun requestAddReview(content: String, score: Float)

        fun requestRemoveReview(review: Review)
    }
}