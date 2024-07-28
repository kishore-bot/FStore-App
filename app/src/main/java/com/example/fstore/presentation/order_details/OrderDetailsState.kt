package com.example.fstore.presentation.order_details

import com.example.fstore.domain.model.FetchOrderModel

data class OrderDetailsState(
    val orderDetails: FetchOrderModel? = null, val id: Int? = null
)