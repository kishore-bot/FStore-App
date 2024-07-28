package com.example.fstore.domain.model.send

data class ProductQuery(
    var category: String? = null,
    var byNew: Int? = 0,
    var byPriceSort: Int = 0,
    var byPopular: Int? = 0,
    var byRating: Int? = 0,
    val color: String? = null,
    var mainCategory: String? = null,
    val available: Int? = null,
    val gender: String? = null,
    val size: String? = null,
    val name: List<String>? = null,
    val lowPrice: Int? = null,
    val highPrice: Int? = null,
)

