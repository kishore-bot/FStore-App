package com.example.fstore.di

import com.example.fstore.data.intercepor.TokenInterceptor
import com.example.fstore.data.remote.ProductApi
import com.example.fstore.data.repo.ProductRepoImp
import com.example.fstore.domain.repo.ProductRepo
import com.example.fstore.domain.usecase.product_usecase.FetchBrands
import com.example.fstore.domain.usecase.product_usecase.FetchCategory
import com.example.fstore.domain.usecase.product_usecase.FetchDetails
import com.example.fstore.domain.usecase.product_usecase.FetchHome
import com.example.fstore.domain.usecase.product_usecase.FetchProducts
import com.example.fstore.domain.usecase.product_usecase.ProductUseCase
import com.example.fstore.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.platform.android.AndroidLogHandler.setLevel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductModule {

    @Provides
    @Singleton
    fun provideProductApi(tokenInterceptor: TokenInterceptor): ProductApi {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepo(productApi: ProductApi): ProductRepo {
        return ProductRepoImp(productApi)
    }

    @Provides
    @Singleton
    fun provideProductUseCase(productRepo: ProductRepo): ProductUseCase {
        return ProductUseCase(
            fetchDetails = FetchDetails(productRepo),
            getProducts = FetchProducts(productRepo),
            fetchBrands = FetchBrands(productRepo),
            fetchCategory = FetchCategory(productRepo),
            fetchHome = FetchHome(productRepo)
        )
    }

}