package com.example.fstore.domain.usecase.order_usercase

import androidx.paging.PagingData
import com.example.fstore.domain.model.rec_sub.Order
import com.example.fstore.domain.repo.OrderRepo
import kotlinx.coroutines.flow.Flow

class FetchOrders (
private val orderRepo: OrderRepo
) {
    suspend operator fun invoke(): Flow<PagingData<Order>> {
        return orderRepo.fetchOrders()
    }
}