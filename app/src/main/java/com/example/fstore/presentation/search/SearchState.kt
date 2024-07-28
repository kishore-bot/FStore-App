package com.example.fstore.presentation.search

import androidx.paging.PagingData
import com.example.fstore.domain.model.rec_sub.Product
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val query: String? = null,
    val products: Flow<PagingData<Product>>? = null,
)
