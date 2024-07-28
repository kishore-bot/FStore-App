package com.example.fstore.domain.usecase.review_UseCase


data class ReviewUseCase(
    val reviewProduct: ReviewProduct, val fetchRating: FetchRating, val fetchReview: FetchReview
)
