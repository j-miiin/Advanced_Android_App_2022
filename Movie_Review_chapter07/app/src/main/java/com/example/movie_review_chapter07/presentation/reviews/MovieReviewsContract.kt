package com.example.movie_review_chapter07.presentation.reviews

import com.example.movie_review_chapter07.domain.model.Movie
import com.example.movie_review_chapter07.domain.model.Review
import com.example.movie_review_chapter07.presentation.BasePresenter
import com.example.movie_review_chapter07.presentation.BaseView

interface MovieReviewsContract {

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showMovieInformation(movie: Movie)

        fun showReviews(reviews: List<Review>)
    }

    interface Presenter : BasePresenter {
        val movie: Movie
    }
}