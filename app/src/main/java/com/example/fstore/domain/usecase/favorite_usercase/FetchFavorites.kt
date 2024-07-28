package com.example.fstore.domain.usecase.favorite_usercase

import androidx.paging.PagingData
import com.example.fstore.domain.model.rec_sub.Favorite
import com.example.fstore.domain.repo.FavoriteRepo
import kotlinx.coroutines.flow.Flow

class FetchFavorites(
    private val favoriteRepo: FavoriteRepo
) {
    suspend operator fun invoke(): Flow<PagingData<Favorite>> {
        return favoriteRepo.fetchFavorites()
    }
}