package com.example.fstore.domain.model.send

data class CreateBagModel(
    val color: String,
    val price: Int = 0,
    val productId: Int,
    val size: String
)