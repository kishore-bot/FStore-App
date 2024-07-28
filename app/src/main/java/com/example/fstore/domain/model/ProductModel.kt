package com.example.fstore.domain.model

import com.example.fstore.domain.model.rec_sub.Product

data class ProductModel(
    val products: List<Product>,
    val total: Int
)