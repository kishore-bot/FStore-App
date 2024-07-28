package com.example.fstore.domain.usecase.product_usecase


data class ProductUseCase(
    val fetchDetails: FetchDetails,
    val getProducts: FetchProducts,
    val fetchBrands: FetchBrands,
    val fetchCategory: FetchCategory,
    val fetchHome: FetchHome
)