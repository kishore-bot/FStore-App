package com.example.fstore.domain.usecase.local_usecase

import com.example.fstore.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(token: String) {
        localUserManager.saveAppEntry(token = token)
    }
}