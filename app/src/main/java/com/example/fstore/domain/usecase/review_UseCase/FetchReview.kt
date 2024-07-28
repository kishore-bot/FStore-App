package com.example.fstore.domain.usecase.review_UseCase

import androidx.paging.PagingData
import com.example.fstore.domain.model.rec_sub.ReviewModel
import com.example.fstore.domain.repo.ReviewRepo
import kotlinx.coroutines.flow.Flow

class FetchReview(
    private val reviewRepo: ReviewRepo
) {
    suspend operator fun invoke(id: Int): Flow<PagingData<ReviewModel>> {
        return reviewRepo.fetchReview(id)
    }
}