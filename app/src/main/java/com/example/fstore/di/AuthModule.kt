package com.example.fstore.di

import com.example.fstore.data.remote.AuthApi
import com.example.fstore.data.repo.AuthRepoImp
import com.example.fstore.domain.repo.AuthRepo
import com.example.fstore.domain.usecase.auth_usecase.AuthUseCase
import com.example.fstore.domain.usecase.auth_usecase.LogIn
import com.example.fstore.domain.usecase.auth_usecase.Register
import com.example.fstore.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepo = AuthRepoImp(authApi)

    @Provides
    @Singleton
    fun provideApiUserCases(authRepository: AuthRepo): AuthUseCase {
        return AuthUseCase(
            register = Register(authRepository),
            logIn = LogIn(authRepository)
        )
    }
}