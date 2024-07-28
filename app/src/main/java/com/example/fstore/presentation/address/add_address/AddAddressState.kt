package com.example.fstore.presentation.address.add_address

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.send.CreateAddressModel

data class AddAddressState(
    val address: CreateAddressModel? = null,
    val message: MessageModel? = null
)