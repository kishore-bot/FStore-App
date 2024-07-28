package com.example.fstore.data.remote

import com.example.fstore.domain.model.BrandsModel
import com.example.fstore.domain.model.CategoryModel
import com.example.fstore.domain.model.DetailsModel
import com.example.fstore.domain.model.ProductModel
import com.example.fstore.utils.Constants.DETAILS_URL
import com.example.fstore.utils.Constants.FETCH_BRANDS
import com.example.fstore.utils.Constants.FETCH_CATEGORIES
import com.example.fstore.utils.Constants.FETCH_PRODUCTS_URL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("$DETAILS_URL/{id}")
    suspend fun getProductDetails(
        @Path("id") id: Int
    ): DetailsModel

    @GET(FETCH_PRODUCTS_URL)
    suspend fun fetchProducts(
        @Query("page") page: Int,
        @Query("category") category: String? = null,
        @Query("byNew") byNew: Int? = 0,
        @Query("byPriceSort") byPriceSort: Int = 0,
        @Query("byPopular") byPopular: Int? = 0,
        @Query("byRating") byRating: Int? = 0,
        @Query("color") color: String? = null,
        @Query("mainCategory") mainCategory: String? = null,
        @Query("available") available: Int? = null,
        @Query("gender") gender: String? = null,
        @Query("size") size: String? = null,
        @Query("name") name: List<String>? = null,
        @Query("lowPrice") lowPrice: Int? = null,
        @Query("highPrice") highPrice: Int? = null

    ): ProductModel


    @GET(FETCH_BRANDS)
    suspend fun getBrands(
        @Query("mainCategory") mainCategory: String
    ): BrandsModel

    @GET(FETCH_CATEGORIES)
    suspend fun getCategories(
        @Query("mainCategory") mainCategory: String, @Query("gender") gender: String
    ): CategoryModel
}