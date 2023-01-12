package com.example.movie_review_chapter07.data.api

import com.example.movie_review_chapter07.domain.model.Movie
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class MovieFirestoreApi(
    private val firestore: FirebaseFirestore
) : MovieApi {

    override suspend fun getAllMovies(): List<Movie> =
        firestore.collection("movies")
            .get()
            .await()
            .map { it.toObject<Movie>() }   // 실제 가져온 데이터는 query snapshot이지만 Movie로 변환

    override suspend fun getMovies(movieIds: List<String>): List<Movie> =
        firestore.collection("movies")
            .whereIn(FieldPath.documentId(), movieIds)// document reference에 있는 id 값이므로 FieldPath로 접근
            .get()
            .await()
            .map { it.toObject<Movie>() }
}