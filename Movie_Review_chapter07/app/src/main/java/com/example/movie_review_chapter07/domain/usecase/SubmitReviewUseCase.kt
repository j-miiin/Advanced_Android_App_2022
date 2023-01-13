package com.example.movie_review_chapter07.domain.usecase

import com.example.movie_review_chapter07.data.repository.ReviewRepository
import com.example.movie_review_chapter07.data.repository.UserRepository
import com.example.movie_review_chapter07.domain.model.Movie
import com.example.movie_review_chapter07.domain.model.Review
import com.example.movie_review_chapter07.domain.model.User

class SubmitReviewUseCase(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        movie: Movie,
        content: String,
        score: Float
    ) : Review {
        val user = userRepository.getUser()

        if (user == null) {
            userRepository.saveUser(User())
            user = userRepository.getUser()
        }

        return reviewRepository.addReview(
            Review(
                
            )
        )
    }
}