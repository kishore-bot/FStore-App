package com.example.fstore.domain.usecase.address_usecase

import com.example.fstore.domain.model.ProfileModel
import com.example.fstore.domain.repo.AddressRepo

class FetchProfile (
    private val addressRepo: AddressRepo
) {
    suspend operator fun invoke(): ProfileModel {
        return addressRepo.fetchProfile()
    }
}