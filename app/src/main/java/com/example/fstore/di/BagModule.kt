package com.example.fstore.di

import com.example.fstore.data.intercepor.TokenInterceptor
import com.example.fstore.data.remote.BagApi
import com.example.fstore.data.repo.BagReoImp
import com.example.fstore.domain.repo.BagRepo
import com.example.fstore.domain.usecase.bag_usecase.BagUseCase
import com.example.fstore.domain.usecase.bag_usecase.CreateBag
import com.example.fstore.domain.usecase.bag_usecase.DeleteBag
import com.example.fstore.domain.usecase.bag_usecase.FetchBags
import com.example.fstore.domain.usecase.bag_usecase.FetchPrice
import com.example.fstore.domain.usecase.bag_usecase.UpdateQuantity
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
class BagModule {
    @Provides
    @Singleton
    fun provideBagApi(tokenInterceptor: TokenInterceptor): BagApi {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(BagApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBagRepo(bagApi: BagApi): BagRepo {
        return BagReoImp(bagApi)
    }

    @Provides
    @Singleton
    fun provideBagUseCase(bagRepo: BagRepo): BagUseCase {
        return BagUseCase(
            createBag = CreateBag(bagRepo),
            fetchBags = FetchBags(bagRepo),
            fetchPrice = FetchPrice(bagRepo),
            deleteBag = DeleteBag(bagRepo),
            updateQuantity = UpdateQuantity(bagRepo)
        )
    }
}