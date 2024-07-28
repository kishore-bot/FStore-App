package com.example.fstore.presentation.details

import com.example.fstore.domain.model.send.CreateBagModel
import com.example.fstore.domain.model.send.CreateFavModel
import com.example.fstore.domain.model.send.ProductQuery
import com.example.fstore.presentation.shop.ShopEvent


sealed class DetailsEvent {
    data class UpdateId(val id: Int) : DetailsEvent()
    data object FetchDetails : DetailsEvent()
    data object GetSimilarProducts : DetailsEvent()
    data class UpdateFavoriteModel(val favorite: CreateFavModel) : DetailsEvent()
    data object PostFavorite : DetailsEvent()
    data object ClearMessage : DetailsEvent()
    data class UploadBag(val bag: CreateBagModel):DetailsEvent()
    data object CreateBag: DetailsEvent()
    data class UploadQuery(val query: ProductQuery) : DetailsEvent()
}