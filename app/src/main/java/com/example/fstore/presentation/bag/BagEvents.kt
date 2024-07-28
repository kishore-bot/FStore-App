package com.example.fstore.presentation.bag

import com.example.fstore.domain.model.rec_sub.Bag
import com.example.fstore.domain.model.send.CreateFavModel


sealed class BagEvents {
    data object FetchBags : BagEvents()
    data object FetchPrice : BagEvents()
    data class UploadId(val id: Int) : BagEvents()
    data object DeleteBag : BagEvents()
    data object ClearMessage : BagEvents()
    data class UploadFav(val fav: CreateFavModel) : BagEvents()
    data object CreateFav : BagEvents()
    data class UpdateQuantity(val bag: Bag) : BagEvents()
    data object UploadBag : BagEvents()
}