package com.example.fstore.data.repo

import com.example.fstore.data.remote.AuthApi
import com.example.fstore.domain.model.AuthModel
import com.example.fstore.domain.model.TokenModel
import com.example.fstore.domain.repo.AuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepoImp(private val authApi: AuthApi) : AuthRepo {
    override suspend fun register(authModel: AuthModel): Flow<TokenModel> {
        return flow{
            emit(authApi.register(authModel))
        }
    }

    override suspend fun login(authModel: AuthModel): Flow<TokenModel> {
        return flow{
            emit(authApi.logIn(authModel))
        }
    }
}