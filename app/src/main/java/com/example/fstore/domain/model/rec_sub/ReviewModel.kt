package com.example.fstore.domain.model.rec_sub

data class ReviewModel(
    val id: Int,
    val comment: String? = null,
    val createdAt: String,
    val rating: Int,
    val user: String
)