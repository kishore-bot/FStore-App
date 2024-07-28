package com.example.fstore.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.fstore.data.paging.ReviewPaging
import com.example.fstore.data.remote.ReviewApi
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.RatingModel
import com.example.fstore.domain.model.rec_sub.ReviewModel
import com.example.fstore.domain.model.send.CreateReviewModel
import com.example.fstore.domain.repo.ReviewRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewRepoImp @Inject constructor(
    private val reviewApi: ReviewApi
) : ReviewRepo {
    override suspend fun createReview(review: CreateReviewModel): MessageModel {
        return reviewApi.createReview(review)
    }

    override suspend fun fetchRating(id: Int): RatingModel {
        return reviewApi.fetchRating(id)
    }

    override suspend fun fetchReview(id: Int): Flow<PagingData<ReviewModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                ReviewPaging(reviewApi = reviewApi, id = id)
            },
        ).flow
    }

}