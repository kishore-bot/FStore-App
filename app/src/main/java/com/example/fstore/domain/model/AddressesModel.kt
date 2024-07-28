package com.example.fstore.domain.model

import com.example.fstore.domain.model.rec_sub.Addresses

data class AddressesModel(
    val addresses: List<Addresses>,
    val primaryAddress: Int
)