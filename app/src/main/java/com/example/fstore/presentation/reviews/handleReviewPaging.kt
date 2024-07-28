package com.example.fstore.presentation.reviews

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.rec_sub.ReviewModel
import com.example.fstore.presentation.utils.handle_paging.PagingState

@Composable
fun handleReviewPaging(reviews: LazyPagingItems<ReviewModel>): PagingState {
    val loadState = reviews.loadState
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