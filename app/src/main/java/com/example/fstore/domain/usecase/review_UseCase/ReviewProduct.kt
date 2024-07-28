package com.example.fstore.domain.usecase.review_UseCase

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.send.CreateReviewModel
import com.example.fstore.domain.repo.ReviewRepo

class ReviewProduct(
    private val reviewRepo: ReviewRepo
) {
    suspend operator fun invoke(review: CreateReviewModel): MessageModel {
        return reviewRepo.createReview(review)
    }
}