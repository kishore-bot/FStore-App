package com.example.fstore.domain.repo

import androidx.paging.PagingData
import com.example.fstore.domain.model.FetchOrderModel
import com.example.fstore.domain.model.IsOrderedModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepo {
    suspend fun placeOrder():MessageModel
    suspend fun fetchOrders():Flow<PagingData<Order>>
    suspend fun fetchOrderById(id:Int):FetchOrderModel
    suspend fun isOrdered(id:Int):IsOrderedModel
}