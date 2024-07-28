package com.example.fstore.domain.usecase.local_usecase

import com.example.fstore.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<String> {
        return localUserManager.readAppEntry()
    }
}