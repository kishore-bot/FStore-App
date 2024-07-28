package com.example.fstore.domain.model

import com.example.fstore.domain.model.rec_sub.ReviewModel

data class FetchReviewModel(
    val review: List<ReviewModel>,
    val total: Int
)