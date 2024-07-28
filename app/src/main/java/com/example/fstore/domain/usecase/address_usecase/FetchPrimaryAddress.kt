package com.example.fstore.domain.usecase.address_usecase

import com.example.fstore.domain.model.rec_sub.Addresses
import com.example.fstore.domain.repo.AddressRepo

class FetchPrimaryAddress (
    private val addressRepo: AddressRepo
) {
    suspend operator fun invoke(): Addresses? {
        return addressRepo.fetchPrimaryAddress()
    }
}