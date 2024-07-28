package com.example.fstore.presentation.logIn

import com.example.fstore.domain.model.AuthModel

sealed class LogInEvent {
    data class UpdateAuthModel(val authModel: AuthModel) : LogInEvent();
    data object LogIn : LogInEvent();
}