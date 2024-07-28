package com.example.fstore.domain.usecase.address_usecase

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.send.CreateAddressModel
import com.example.fstore.domain.repo.AddressRepo

class CreateAddress(
    private val addressRepo: AddressRepo
) {
    suspend operator fun invoke(address: CreateAddressModel): MessageModel {
        return addressRepo.createAddress(address)
    }
}