package com.example.fstore.domain.repo

import com.example.fstore.domain.model.AuthModel
import com.example.fstore.domain.model.TokenModel
import kotlinx.coroutines.flow.Flow

interface AuthRepo {
    suspend fun register(authModel: AuthModel): Flow<TokenModel>
    suspend fun login(authModel: AuthModel): Flow<TokenModel>
}