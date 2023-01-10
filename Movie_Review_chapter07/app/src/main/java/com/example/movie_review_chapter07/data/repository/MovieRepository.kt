package com.example.movie_review_chapter07.data.repository

import com.example.movie_review_chapter07.domain.model.Movie

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>
}