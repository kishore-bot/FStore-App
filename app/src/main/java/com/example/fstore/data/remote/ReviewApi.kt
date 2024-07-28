package com.example.fstore.data.remote

import com.example.fstore.domain.model.FetchReviewModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.RatingModel
import com.example.fstore.domain.model.send.CreateReviewModel
import com.example.fstore.utils.Constants.RATING_URL
import com.example.fstore.utils.Constants.REVIEW_URL
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReviewApi {
    @POST(REVIEW_URL)
    suspend fun createReview(
        @Body createReviewModel: CreateReviewModel
    ): MessageModel

    @GET(RATING_URL)
    suspend fun fetchRating(
        @Query("id") id: Int
    ): RatingModel

    @GET(REVIEW_URL)
    suspend fun fetchReview(
        @Query("id") id: Int, @Query("page") page: Int
    ): FetchReviewModel
}