package com.example.fstore.domain.usecase.order_usercase


import com.example.fstore.domain.model.FetchOrderModel
import com.example.fstore.domain.repo.OrderRepo


class FetchOrderById(
    private val orderRepo: OrderRepo
) {
    suspend operator fun invoke(id: Int): FetchOrderModel {
        return orderRepo.fetchOrderById(id)
    }
}