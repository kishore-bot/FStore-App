package com.example.fstore.domain.model.rec_sub

data class Order(
    val createdAt: String,
    val id: Int,
    val orderId: String,
    val orderNo: String,
    val quantity: Int,
    val totalPrice: Int
)