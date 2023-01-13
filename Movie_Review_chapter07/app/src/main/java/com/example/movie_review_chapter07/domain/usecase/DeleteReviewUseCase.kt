package com.example.movie_review_chapter07.domain.usecase

import com.example.movie_review_chapter07.data.repository.ReviewRepository
import com.example.movie_review_chapter07.domain.model.Review

class DeleteReviewUseCase(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(review: Review) =
        reviewRepository.removeReview(review)
}