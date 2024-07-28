package com.example.fstore.domain.model.rec_sub

data class Product(
    val category: String,
    val dPrice: Int,
    val discount: Int,
    val id: Int,
    val name: String,
    val new: Boolean,
    val price: Double,
    val rating: Int,
    val isFav: Boolean,
    val noOfRating: Int,
    val thumbnailUrl:String
)