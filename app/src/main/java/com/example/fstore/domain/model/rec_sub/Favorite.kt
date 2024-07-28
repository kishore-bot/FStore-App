package com.example.fstore.domain.model.rec_sub

data class Favorite(
    val available: Boolean,
    val category: String,
    val color: String,
    val discount: Int,
    val id: Int,
    val name: String,
    val new: Boolean,
    val popularity: Int,
    val price: Int,
    val rating: Int,
    val size: String,
    val dPrice: Int,
    val noOfRating: Int,
    val thumbnailUrl: String
)