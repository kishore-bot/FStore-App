package com.example.fstore.domain.repo

import androidx.paging.PagingData
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Favorite
import com.example.fstore.domain.model.send.CreateFavModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepo {
    suspend fun postFavoriteRepo(createFavModel: CreateFavModel): MessageModel
    suspend fun fetchFavorites(): Flow<PagingData<Favorite>>
    suspend fun deleteFavorites(id: Int): MessageModel
}