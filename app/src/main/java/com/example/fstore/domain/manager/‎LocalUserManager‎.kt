package com.example.fstore.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry(token: String)
    fun readAppEntry(): Flow<String>
    suspend fun deleteAppEntry()
}