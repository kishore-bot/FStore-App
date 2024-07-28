package com.example.fstore.presentation.home

import com.example.fstore.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

data class HomeState(val products: Flow<ProductModel>? = null)