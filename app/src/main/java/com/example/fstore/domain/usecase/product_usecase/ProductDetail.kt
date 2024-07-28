package com.example.fstore.domain.usecase.product_usecase

import com.example.fstore.domain.model.DetailsModel
import com.example.fstore.domain.repo.ProductRepo

class FetchDetails(
    private val productRepo: ProductRepo
) {
    suspend operator fun invoke(id: Int): DetailsModel {
        return productRepo.getProductDetails(id)
    }
}
