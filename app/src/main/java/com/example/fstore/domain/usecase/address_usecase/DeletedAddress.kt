package com.example.fstore.domain.usecase.address_usecase

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.repo.AddressRepo

class DeletedAddress(
    private val addressRepo: AddressRepo
) {
    suspend operator fun invoke(id: Int): MessageModel {
        return addressRepo.deleteAddress(id)
    }
}