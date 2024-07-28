package com.example.fstore.domain.usecase.favorite_usercase

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.send.CreateFavModel
import com.example.fstore.domain.repo.FavoriteRepo

class PostFavorite(
    private val favoriteRepo: FavoriteRepo
) {
    suspend operator fun invoke(createFavModel: CreateFavModel): MessageModel {
        return favoriteRepo.postFavoriteRepo(createFavModel)
    }
}