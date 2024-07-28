package com.example.fstore.di

import com.example.fstore.data.intercepor.TokenInterceptor
import com.example.fstore.data.remote.FavoriteApi
import com.example.fstore.data.repo.FavoriteRepoImp
import com.example.fstore.domain.repo.FavoriteRepo
import com.example.fstore.domain.usecase.favorite_usercase.DeleteFavorite
import com.example.fstore.domain.usecase.favorite_usercase.FavoriteUseCase
import com.example.fstore.domain.usecase.favorite_usercase.FetchFavorites
import com.example.fstore.domain.usecase.favorite_usercase.PostFavorite
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
class FavoriteModule {
    @Provides
    @Singleton
    fun provideFavoriteApi(tokenInterceptor: TokenInterceptor): FavoriteApi {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(FavoriteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepo(favoriteApi: FavoriteApi): FavoriteRepo {
        return FavoriteRepoImp(favoriteApi)
    }

    @Provides
    @Singleton
    fun provideFavoriteUseCase(favoriteRepo: FavoriteRepo): FavoriteUseCase {
        return FavoriteUseCase(
            postFavorite = PostFavorite(favoriteRepo),
            fetchFavorites = FetchFavorites(favoriteRepo),
            deleteFavorite = DeleteFavorite(favoriteRepo)
        )
    }
}