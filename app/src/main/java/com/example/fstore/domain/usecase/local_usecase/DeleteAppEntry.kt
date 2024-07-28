package com.example.fstore.domain.usecase.local_usecase

import com.example.fstore.domain.manager.LocalUserManager

class DeleteAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.deleteAppEntry()
    }
}