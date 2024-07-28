package com.example.fstore.domain.usecase.product_usecase

import com.example.fstore.domain.model.ProductModel
import com.example.fstore.domain.repo.ProductRepo
import kotlinx.coroutines.flow.Flow

class FetchHome(
    private val productRepo: ProductRepo
) {
    suspend operator fun invoke(): Flow<ProductModel> {
        return productRepo.homeNewProducts()
    }
}