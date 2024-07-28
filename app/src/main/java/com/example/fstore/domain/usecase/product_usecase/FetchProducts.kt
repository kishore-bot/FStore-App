package com.example.fstore.domain.usecase.product_usecase

import androidx.paging.PagingData
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.domain.model.send.ProductQuery
import com.example.fstore.domain.repo.ProductRepo
import kotlinx.coroutines.flow.Flow

class FetchProducts(
    private val productRepo: ProductRepo
) {
    suspend operator fun invoke(query: ProductQuery): Flow<PagingData<Product>> {
        return productRepo.getProducts(query)
    }
}