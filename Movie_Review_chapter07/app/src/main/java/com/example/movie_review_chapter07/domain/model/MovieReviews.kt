package com.example.movie_review_chapter07.domain.model

data class MovieReviews(
    val myReview: Review?,
    val othersReview: List<Review>
)
