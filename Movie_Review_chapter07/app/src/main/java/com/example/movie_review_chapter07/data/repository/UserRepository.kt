package com.example.movie_review_chapter07.data.repository

import com.example.movie_review_chapter07.domain.model.User

interface UserRepository {

    suspend fun getUser(): User?

    suspend fun saveUser(user: User)
}