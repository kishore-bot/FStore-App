package com.example.fstore.domain.usecase.order_usercase

import com.example.fstore.domain.model.IsOrderedModel
import com.example.fstore.domain.repo.OrderRepo

class IsOrdered(
    private val orderRepo: OrderRepo
) {
    suspend operator fun invoke(id: Int): IsOrderedModel {
        return orderRepo.isOrdered(id)
    }
}