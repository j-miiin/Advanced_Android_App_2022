package com.example.movie_review_chapter07.presentation.home

import com.example.movie_review_chapter07.domain.model.FeaturedMovie
import com.example.movie_review_chapter07.domain.model.Movie
import com.example.movie_review_chapter07.presentation.BasePresenter
import com.example.movie_review_chapter07.presentation.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showMovies(
            featuredMovie: FeaturedMovie?,
            movies: List<Movie>
        )
    }

    interface Presenter : BasePresenter
}