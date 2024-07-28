package com.example.fstore.domain.usecase.order_usercase

data class OrderUseCase(
    val placeOrder: PlaceOrder,
    val fetchOrders: FetchOrders,
    val fetchOrderById: FetchOrderById,
    val isOrdered: IsOrdered
)
