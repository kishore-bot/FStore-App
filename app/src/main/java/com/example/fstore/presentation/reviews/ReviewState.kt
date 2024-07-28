package com.example.fstore.presentation.reviews

import androidx.paging.PagingData
import com.example.fstore.domain.model.IsOrderedModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.RatingModel
import com.example.fstore.domain.model.rec_sub.ReviewModel
import com.example.fstore.domain.model.send.CreateReviewModel
import kotlinx.coroutines.flow.Flow

data class ReviewState(
    val isOrdered: IsOrderedModel? = null,
    val createReviewModel: CreateReviewModel? = null,
    val id: Int? = null,
    val messageModel: MessageModel? = null,
    val ratingModel: RatingModel? = null,
    val review: Flow<PagingData<ReviewModel>>? = null
)