package com.example.fstore.domain.usecase.address_usecase

import com.example.fstore.domain.model.AddressesModel
import com.example.fstore.domain.repo.AddressRepo

class FetchAddress(
    private val addressRepo: AddressRepo
) {
    suspend operator fun invoke():AddressesModel {
        return addressRepo.fetchAddress()
    }
}