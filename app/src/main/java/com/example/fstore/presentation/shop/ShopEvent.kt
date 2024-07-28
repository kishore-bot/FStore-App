package com.example.fstore.presentation.shop

import com.example.fstore.domain.model.send.ProductQuery

sealed class ShopEvent {
    data object FetchBrands : ShopEvent()
    data class UploadQuery(val query: ProductQuery) : ShopEvent()
    data object ClearQuery : ShopEvent()
    data object FetchProducts : ShopEvent()
    data object FetchCategories : ShopEvent()
}