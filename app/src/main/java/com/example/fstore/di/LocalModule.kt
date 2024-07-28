package com.example.fstore.di

import android.app.Application
import com.example.fstore.data.manager.LocalUserManagerImp
import com.example.fstore.domain.manager.LocalUserManager
import com.example.fstore.domain.usecase.local_usecase.AppEntryUseCases
import com.example.fstore.domain.usecase.local_usecase.DeleteAppEntry
import com.example.fstore.domain.usecase.local_usecase.ReadAppEntry
import com.example.fstore.domain.usecase.local_usecase.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImp(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCase(localUserManager: LocalUserManager): AppEntryUseCases =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager),
            deleteAppEntry = DeleteAppEntry(localUserManager)
        )
}