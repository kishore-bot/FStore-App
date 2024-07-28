package com.example.fstore.di

import com.example.fstore.data.intercepor.TokenInterceptor
import com.example.fstore.domain.manager.LocalUserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProvideToken {
    @Provides
    @Singleton
    fun provideTokenHeader(localUserManager: LocalUserManager): TokenInterceptor {
        return TokenInterceptor(localUserManager)
    }
}