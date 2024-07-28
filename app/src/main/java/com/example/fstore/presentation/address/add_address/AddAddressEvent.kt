package com.example.fstore.presentation.address.add_address

import com.example.fstore.domain.model.send.CreateAddressModel

sealed class AddAddressEvent {
    data class UpdateAddress(val address: CreateAddressModel):AddAddressEvent()
    data object CreateAddress:AddAddressEvent()
}