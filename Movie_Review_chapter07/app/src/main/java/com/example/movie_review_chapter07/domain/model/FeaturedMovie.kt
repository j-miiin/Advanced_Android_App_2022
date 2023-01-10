package com.example.movie_review_chapter07.domain.model

data class FeaturedMovie(
    val movie: Movie,
    val latestReview: Review?
)
