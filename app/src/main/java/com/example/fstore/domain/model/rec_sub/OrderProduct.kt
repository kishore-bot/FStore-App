package com.example.fstore.domain.model.rec_sub

data class OrderProduct(
    val category: String,
    val color: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val size: String,
    val thumbnailUrl:String
)