package com.example.fstore.presentation.register

import com.example.fstore.domain.model.AuthModel

sealed class RegisterEvent {
    data class UpdateAuthModel(val authModel: AuthModel):RegisterEvent();
    data object Register:RegisterEvent();
}