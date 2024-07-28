package com.example.fstore.data.remote

import com.example.fstore.domain.model.send.CreateFavModel
import com.example.fstore.domain.model.FavoriteModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.utils.Constants.FAV_URL
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteApi {
    @POST(FAV_URL)
    suspend fun postFav(
        @Body createFavModel: CreateFavModel
    ): MessageModel

    @GET("$FAV_URL/{page}")
    suspend fun fetchFavorites(
        @Path("page") page: Int
    ): FavoriteModel

    @DELETE("$FAV_URL/{id}")
    suspend fun deleteFavorite(
        @Path("id") id: Int
    ): MessageModel

}
