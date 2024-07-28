package com.example.fstore.domain.model

data class AuthModel(
    val email: String, val name: String? = null, val password: String
)