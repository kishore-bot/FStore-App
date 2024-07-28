package com.example.fstore.presentation.orders

import androidx.paging.PagingData
import com.example.fstore.domain.model.rec_sub.Order
import kotlinx.coroutines.flow.Flow

data class OrderState(
    val orders: Flow<PagingData<Order>>? = null
)