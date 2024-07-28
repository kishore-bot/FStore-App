package com.example.fstore.presentation.utils.handle_paging

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.rec_sub.Product

@Composable
fun handleProductPagingResult(product: LazyPagingItems<Product>): PagingState {
    val loadState = product.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            PagingState.INITIAL_LOADING
        }

        loadState.append is LoadState.Loading -> {
            PagingState.NEW_DATA_LOADING
        }

        error != null -> {
            Log.d("LazyPagingError", "Something Went Wrong")
            PagingState.ERROR
        }

        else -> {
            PagingState.SUCCESS
        }
    }
}