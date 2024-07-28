package com.example.fstore.domain.usecase.favorite_usercase

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.repo.FavoriteRepo

class DeleteFavorite(
    private val favoriteRepo: FavoriteRepo
) {
    suspend operator fun invoke(id: Int): MessageModel {
        return favoriteRepo.deleteFavorites(id)
    }
}