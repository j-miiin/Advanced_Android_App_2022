package com.example.movie_review_chapter07.data.api

import com.example.movie_review_chapter07.domain.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserFirestoreApi(
    private val firestore: FirebaseFirestore
) : UserApi {

    override suspend fun saveUser(user: User): User =
        firestore.collection("users")
            .add(user)  // 추가에는 add, set, update가 있는데 set은 id를 명확하게 명시해야 함 (동일하면 덮어씌우고 아니면 새로 추가)
            .await()
            .let { User(it.id) }
}