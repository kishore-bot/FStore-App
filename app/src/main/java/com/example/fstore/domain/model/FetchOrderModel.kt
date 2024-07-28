package com.example.fstore.domain.model

import com.example.fstore.domain.model.rec_sub.OrderAddressModel
import com.example.fstore.domain.model.rec_sub.OrderProduct

data class FetchOrderModel(
    val address: OrderAddressModel,
    val createdAt: String,
    val id: Int,
    val orderId: String,
    val orderNo: String,
    val orderProducts: List<OrderProduct>,
    val quantity: Int,
    val totalPrice: Int
)