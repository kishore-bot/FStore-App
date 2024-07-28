package com.example.fstore.presentation.shop

import androidx.paging.PagingData
import com.example.fstore.domain.model.BrandsModel
import com.example.fstore.domain.model.CategoryModel
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.domain.model.send.ProductQuery
import kotlinx.coroutines.flow.Flow

data class ShopState(
    val brands: Flow<BrandsModel>? = null,
    val query: ProductQuery? = null,
    val products: Flow<PagingData<Product>>? = null,
    val categories: Flow<CategoryModel>? = null
)