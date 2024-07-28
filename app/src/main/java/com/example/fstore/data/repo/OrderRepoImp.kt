package com.example.fstore.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.fstore.data.paging.OrdersPaging
import com.example.fstore.data.remote.OrderApi
import com.example.fstore.domain.model.FetchOrderModel
import com.example.fstore.domain.model.IsOrderedModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Order
import com.example.fstore.domain.repo.OrderRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepoImp @Inject constructor(
    private val orderApi: OrderApi
) : OrderRepo {
    override suspend fun placeOrder(): MessageModel {
        val response = orderApi.placeOrder()
        return response;
    }

    override suspend fun fetchOrders(): Flow<PagingData<Order>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                OrdersPaging(orderApi = orderApi)
            },
        ).flow
    }

    override suspend fun fetchOrderById(id: Int): FetchOrderModel {
        return orderApi.fetchOrdersById(id)
    }

    override suspend fun isOrdered(id: Int): IsOrderedModel {
        return orderApi.isOrdered(id)
    }

}