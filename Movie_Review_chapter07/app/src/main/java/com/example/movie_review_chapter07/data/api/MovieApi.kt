package com.example.movie_review_chapter07.data.api

import com.example.movie_review_chapter07.domain.model.Movie

interface MovieApi {

    suspend fun getAllMovies(): List<Movie>
}