package com.example.fstore.domain.model

import com.example.fstore.domain.model.rec_sub.Order

data class OrdersModel(
    val orders: List<Order>,
    val total: Int
)