package com.example.fstore.domain.usecase.bag_usecase

import com.example.fstore.domain.model.BagPriceModel
import com.example.fstore.domain.repo.BagRepo
import kotlinx.coroutines.flow.Flow

class FetchPrice(
    private val bagRepo: BagRepo
) {
    suspend operator fun invoke(): Flow<BagPriceModel> {
        return bagRepo.getPrice()
    }
}