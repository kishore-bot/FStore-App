package com.example.fstore.di

import com.example.fstore.data.intercepor.TokenInterceptor
import com.example.fstore.data.remote.AddressApi
import com.example.fstore.data.repo.AddressRepoImp
import com.example.fstore.domain.repo.AddressRepo
import com.example.fstore.domain.usecase.address_usecase.AddressUseCase
import com.example.fstore.domain.usecase.address_usecase.ChangeAddress
import com.example.fstore.domain.usecase.address_usecase.CreateAddress
import com.example.fstore.domain.usecase.address_usecase.DeletedAddress
import com.example.fstore.domain.usecase.address_usecase.FetchAddress
import com.example.fstore.domain.usecase.address_usecase.FetchPrimaryAddress
import com.example.fstore.domain.usecase.address_usecase.FetchProfile
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
class AddressModule {
    @Provides
    @Singleton
    fun provideAddressApi(tokenInterceptor: TokenInterceptor): AddressApi {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(AddressApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAddressRepo(addressApi: AddressApi): AddressRepo {
        return AddressRepoImp(addressApi)
    }

    @Provides
    @Singleton
    fun provideAddressUseCase(addressRepo: AddressRepo): AddressUseCase {
        return AddressUseCase(
            createAddress = CreateAddress(addressRepo),
            fetchAddress = FetchAddress(addressRepo),
            deletedAddress = DeletedAddress(addressRepo),
            changeAddress = ChangeAddress(addressRepo),
            fetchPrimaryAddress = FetchPrimaryAddress(addressRepo),
            fetchProfile = FetchProfile(addressRepo)
        )
    }
}