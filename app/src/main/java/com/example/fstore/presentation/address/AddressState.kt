package com.example.fstore.presentation.address

import com.example.fstore.domain.model.AddressesModel
import com.example.fstore.domain.model.MessageModel

data class AddressState(
    val addressesModel: AddressesModel? = null,
    val messageModel: MessageModel? = null,
    val id: Int? = null
)