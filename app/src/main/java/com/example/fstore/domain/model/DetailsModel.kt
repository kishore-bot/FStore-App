package com.example.fstore.domain.model

data class DetailsModel(
    val available: Boolean,
    val category: String,
    val color: List<String>,
    val description: String,
    val mainCategory: String,
    val gender: String,
    val id: Int,
    val name: String,
    val popularity: Int,
    val price: Double,
    val rating: Int,
    val size: List<String>,
    var favorite: Boolean,
    val noOfRating: Int,
    val imageUrls:List<String>
)