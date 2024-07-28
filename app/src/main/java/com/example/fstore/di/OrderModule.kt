package com.example.fstore.di

import com.example.fstore.data.intercepor.TokenInterceptor
import com.example.fstore.data.remote.OrderApi
import com.example.fstore.data.repo.OrderRepoImp
import com.example.fstore.domain.repo.OrderRepo
import com.example.fstore.domain.usecase.order_usercase.FetchOrderById
import com.example.fstore.domain.usecase.order_usercase.FetchOrders
import com.example.fstore.domain.usecase.order_usercase.IsOrdered
import com.example.fstore.domain.usecase.order_usercase.OrderUseCase
import com.example.fstore.domain.usecase.order_usercase.PlaceOrder
import com.example.fstore.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OrderModule {
    @Provides
    @Singleton
    fun provideOrderApi(tokenInterceptor: TokenInterceptor): OrderApi {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(OrderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOrderRepo(orderApi: OrderApi): OrderRepo {
        return OrderRepoImp(orderApi)
    }

    @Provides
    @Singleton
    fun provideOrderUseCase(orderRepo: OrderRepo): OrderUseCase {
        return OrderUseCase(
            placeOrder = PlaceOrder(orderRepo),
            fetchOrders = FetchOrders(orderRepo),
            fetchOrderById = FetchOrderById(orderRepo),
            isOrdered = IsOrdered(orderRepo)
        )
    }
}