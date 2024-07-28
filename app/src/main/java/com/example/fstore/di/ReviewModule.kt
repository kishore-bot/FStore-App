package com.example.fstore.di

import com.example.fstore.data.intercepor.TokenInterceptor
import com.example.fstore.data.remote.ReviewApi
import com.example.fstore.data.repo.ReviewRepoImp
import com.example.fstore.domain.repo.ReviewRepo
import com.example.fstore.domain.usecase.review_UseCase.FetchRating
import com.example.fstore.domain.usecase.review_UseCase.FetchReview
import com.example.fstore.domain.usecase.review_UseCase.ReviewProduct
import com.example.fstore.domain.usecase.review_UseCase.ReviewUseCase
import com.example.fstore.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReviewModule {
    @Provides
    @Singleton
    fun provideReviewApi(tokenInterceptor: TokenInterceptor): ReviewApi {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ReviewApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewRepo(reviewApi: ReviewApi): ReviewRepo {
        return ReviewRepoImp(reviewApi = reviewApi)
    }

    @Provides
    @Singleton
    fun provideReviewUseCase(reviewRepo: ReviewRepo): ReviewUseCase {
        return ReviewUseCase(
            reviewProduct = ReviewProduct(reviewRepo),
            fetchRating = FetchRating(reviewRepo),
            fetchReview = FetchReview(reviewRepo)
        )
    }
}