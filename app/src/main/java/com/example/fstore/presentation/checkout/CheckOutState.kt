package com.example.fstore.presentation.checkout

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Addresses

data class CheckOutState(
    val address: Addresses? = null, val messageModel: MessageModel? = null
)