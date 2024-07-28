package com.example.fstore.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.fstore.data.paging.FavoritePaging
import com.example.fstore.data.remote.FavoriteApi
import com.example.fstore.domain.model.send.CreateFavModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Favorite
import com.example.fstore.domain.repo.FavoriteRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepoImp @Inject constructor(
    private val favoriteApi: FavoriteApi
) : FavoriteRepo {
    override suspend fun postFavoriteRepo(createFavModel: CreateFavModel): MessageModel {
        val messageModel = favoriteApi.postFav(createFavModel)
        return messageModel
    }

    override suspend fun fetchFavorites(): Flow<PagingData<Favorite>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            FavoritePaging(favoriteApi = favoriteApi)
        }).flow
    }

    override suspend fun deleteFavorites(id: Int): MessageModel {
        val message = favoriteApi.deleteFavorite(id = id)
        return message
    }
}