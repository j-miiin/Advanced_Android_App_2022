package com.example.movie_review_chapter07.data.repository

import com.example.movie_review_chapter07.domain.model.Review

interface ReviewRepository {

    suspend fun getLatestReview(movieId: String): Review?

    suspend fun getAllReviews(movieId: String): List<Review>
}