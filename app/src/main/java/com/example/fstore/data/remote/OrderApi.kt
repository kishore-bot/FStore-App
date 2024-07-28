package com.example.fstore.data.remote

import com.example.fstore.domain.model.FetchOrderModel
import com.example.fstore.domain.model.IsOrderedModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.OrdersModel
import com.example.fstore.utils.Constants.IS_PRODUCT_ORDERED
import com.example.fstore.utils.Constants.ORDER_URL
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {

    @POST(ORDER_URL)
    suspend fun placeOrder(): MessageModel

    @GET("$ORDER_URL/{page}")
    suspend fun fetchOrders(
        @Path("page") page: Int
    ): OrdersModel

    @GET("$ORDER_URL/by/{id}")
    suspend fun fetchOrdersById(
        @Path("id") id: Int
    ): FetchOrderModel

    @GET("$IS_PRODUCT_ORDERED/{id}")
    suspend fun isOrdered(
        @Path("id") id: Int
    ): IsOrderedModel
}

