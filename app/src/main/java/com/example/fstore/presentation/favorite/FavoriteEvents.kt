package com.example.fstore.presentation.favorite

import com.example.fstore.domain.model.send.CreateBagModel

sealed class FavoriteEvents {
    data object FetchFavorites : FavoriteEvents()
    data class UploadId(val id: Int) : FavoriteEvents()
    data object DeleteFavorite : FavoriteEvents()
    data object ClearMessage : FavoriteEvents()
    data class UploadBag(val bag: CreateBagModel):FavoriteEvents()
    data object CreateBag:FavoriteEvents()
}