package com.example.fstore.domain.usecase.order_usercase

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.repo.OrderRepo

class PlaceOrder(
    private val orderRepo: OrderRepo
) {
    suspend operator fun invoke():MessageModel{
        return orderRepo.placeOrder()
    }
}