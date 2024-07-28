package com.example.fstore.presentation.reviews

import com.example.fstore.domain.model.send.CreateReviewModel

sealed class ReviewEvent {
    data class UploadId(val id: Int) : ReviewEvent()
    data class UploadReview(val review: CreateReviewModel) : ReviewEvent()
    data object ReviewProduct : ReviewEvent()
    data object IsOrderedCheck : ReviewEvent()
    data object CLearMessage : ReviewEvent()
    data object FetchRating : ReviewEvent()
    data object FetchReview : ReviewEvent()
}
