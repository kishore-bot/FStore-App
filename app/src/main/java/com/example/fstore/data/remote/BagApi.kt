package com.example.fstore.data.remote

import com.example.fstore.domain.model.BagModel
import com.example.fstore.domain.model.BagPriceModel
import com.example.fstore.domain.model.send.CreateBagModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.send.BagQuantityModel
import com.example.fstore.utils.Constants.BAG_PRICE_URL
import com.example.fstore.utils.Constants.BAG_URL
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface BagApi {
    @POST(BAG_URL)
    suspend fun createBag(
        @Body bag: CreateBagModel
    ): MessageModel

    @GET("$BAG_URL/{page}")
    suspend fun getPage(
        @Path("page") page: Int
    ): BagModel

    @GET(BAG_PRICE_URL)
    suspend fun getPrice(): BagPriceModel

    @DELETE("$BAG_URL/{page}")
    suspend fun deleteBag(
        @Path("page") page: Int
    ): MessageModel

    @PATCH(BAG_URL)
    suspend fun updateQuantity(
        @Body quantity: BagQuantityModel
    ): MessageModel
}