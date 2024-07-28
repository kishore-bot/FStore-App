package com.example.fstore.domain.usecase.product_usecase

import com.example.fstore.domain.model.BrandsModel
import com.example.fstore.domain.repo.ProductRepo
import kotlinx.coroutines.flow.Flow

class FetchBrands(
    private val productRepo: ProductRepo
) {
    suspend operator fun invoke(mainCategory: String): Flow<BrandsModel> {
        return productRepo.fetchBrands(mainCategory)
    }
}