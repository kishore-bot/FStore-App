package com.example.fstore.domain.model

import com.example.fstore.domain.model.rec_sub.StarCountsModel

data class RatingModel(
    val productRating: Int,
    val starCounts: StarCountsModel,
    val totalRating: Int
)