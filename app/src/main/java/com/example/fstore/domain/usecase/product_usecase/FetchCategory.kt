package com.example.fstore.domain.usecase.product_usecase

import com.example.fstore.domain.model.CategoryModel
import com.example.fstore.domain.repo.ProductRepo
import kotlinx.coroutines.flow.Flow

class FetchCategory(
    private val productRepo: ProductRepo
) {
    suspend operator fun invoke(mainCategory: String, gender: String): Flow<CategoryModel> {
        return productRepo.fetchCategory(mainCategory = mainCategory, gender = gender)
    }
}