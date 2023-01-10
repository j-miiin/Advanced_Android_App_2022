package com.example.movie_review_chapter07.data.api

import com.example.movie_review_chapter07.domain.model.Review

interface ReviewApi {

    suspend fun getLatestReview(movieId: String): Review?
}