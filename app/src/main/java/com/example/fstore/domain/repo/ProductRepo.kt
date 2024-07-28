package com.example.fstore.domain.repo

import androidx.paging.PagingData
import com.example.fstore.domain.model.BrandsModel
import com.example.fstore.domain.model.CategoryModel
import com.example.fstore.domain.model.DetailsModel
import com.example.fstore.domain.model.ProductModel
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.domain.model.send.ProductQuery
import kotlinx.coroutines.flow.Flow

interface ProductRepo {
    suspend fun getProductDetails(id: Int): DetailsModel
    suspend fun getProducts(query: ProductQuery): Flow<PagingData<Product>>
    suspend fun fetchBrands(mainCategory: String): Flow<BrandsModel>
    suspend fun fetchCategory(mainCategory: String, gender: String): Flow<CategoryModel>
    suspend fun homeNewProducts(): Flow<ProductModel>
}