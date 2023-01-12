package com.example.movie_review_chapter07.data.api

import com.example.movie_review_chapter07.domain.model.User


interface UserApi {

    suspend fun saveUser(user: User): User
}