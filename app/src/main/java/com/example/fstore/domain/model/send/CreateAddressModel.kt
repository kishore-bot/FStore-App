package com.example.fstore.domain.model.send

data class CreateAddressModel(
    val address: String,
    val city: String,
    val country: String,
    val name: String,
    val pincode: Int,
    val state: String
)