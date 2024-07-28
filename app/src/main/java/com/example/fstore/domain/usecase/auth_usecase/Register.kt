package com.example.fstore.domain.usecase.auth_usecase

import com.example.fstore.domain.model.AuthModel
import com.example.fstore.domain.model.TokenModel
import com.example.fstore.domain.repo.AuthRepo
import kotlinx.coroutines.flow.Flow

class Register(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke(authModel: AuthModel): Flow<TokenModel> {
        return authRepo.register(authModel)
    }
}