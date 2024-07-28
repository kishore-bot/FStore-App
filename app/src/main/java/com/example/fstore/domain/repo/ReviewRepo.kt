package com.example.fstore.domain.repo

import androidx.paging.PagingData
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.RatingModel
import com.example.fstore.domain.model.rec_sub.ReviewModel
import com.example.fstore.domain.model.send.CreateReviewModel
import kotlinx.coroutines.flow.Flow

interface ReviewRepo {
    suspend fun createReview(review: CreateReviewModel): MessageModel
    suspend fun fetchRating(id: Int): RatingModel
    suspend fun fetchReview(id: Int): Flow<PagingData<ReviewModel>>
}