package com.example.fstore.domain.usecase.review_UseCase


import com.example.fstore.domain.model.RatingModel
import com.example.fstore.domain.repo.ReviewRepo

class FetchRating(
    private val reviewRepo: ReviewRepo
) {
    suspend operator fun invoke(id: Int): RatingModel {
        return reviewRepo.fetchRating(id)
    }
}