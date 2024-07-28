package com.example.fstore.data.remote

import com.example.fstore.domain.model.AuthModel
import com.example.fstore.domain.model.TokenModel
import com.example.fstore.utils.Constants.LOGIN_URL
import com.example.fstore.utils.Constants.REGISTER_URL
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST(REGISTER_URL)
    suspend fun register(
        @Body authModel: AuthModel
    ):TokenModel

    @POST(LOGIN_URL)
    suspend fun logIn(
        @Body authModel: AuthModel
    ):TokenModel
}