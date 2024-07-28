package com.example.fstore.domain.model.rec_sub

data class Bag(
    val available: Boolean,
    val category: String,
    val color: String,
    val id: Int,
    val price: Int,
    val productId: Int,
    var quantity: Int,
    val size: String,
    val thumbnailUrl: String
)