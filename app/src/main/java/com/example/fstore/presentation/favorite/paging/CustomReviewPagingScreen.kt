package com.example.fstore.presentation.favorite.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.rec_sub.ReviewModel
import com.example.fstore.presentation.reviews.components.CustomReviewCard
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar
import com.example.fstore.presentation.utils.handle_paging.PagingState


@Composable
fun CustomReviewPagingScreen(
    modifier: Modifier = Modifier, pagingState: PagingState, reviews: LazyPagingItems<ReviewModel>
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(reviews.itemCount) { index ->
            reviews[index]?.let { review ->
                if (review.comment != "") {
                    CustomReviewCard(review = review)
                }
            }
        }
        if (pagingState == PagingState.NEW_DATA_LOADING) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CustomMultiColoredProgressBar()
                }
            }
        }
    }
}