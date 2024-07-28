package com.example.fstore.domain.usecase.local_usecase

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry,
    val deleteAppEntry: DeleteAppEntry
)