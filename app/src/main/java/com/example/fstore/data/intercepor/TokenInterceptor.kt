package com.example.fstore.data.intercepor

import com.example.fstore.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val localUserManager: LocalUserManager,
) : Interceptor {
    private var token: String = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        if (token.isEmpty()) {
            token = runBlocking {
                localUserManager.readAppEntry().first()
            }
        }
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}
