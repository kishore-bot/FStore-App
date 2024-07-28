package com.example.fstore.domain.model.send

data class CreateReviewModel(
    val comment: String? = null,
    val rating: Int,
    val productId: Int
)
