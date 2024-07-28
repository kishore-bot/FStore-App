package com.example.fstore.presentation.reviews


import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fstore.presentation.favorite.paging.CustomReviewPagingScreen
import com.example.fstore.presentation.reviews.components.CustomReviewCard
import com.example.fstore.presentation.reviews.components.CustomReviewCheckSection
import com.example.fstore.presentation.reviews.components.CustomReviewStatSection
import com.example.fstore.presentation.reviews.components.CustomReviewTopAppBar
import com.example.fstore.presentation.reviews.create_review.CreateReview
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar
import com.example.fstore.presentation.utils.handle_paging.PagingState
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.utils.showToastMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    modifier: Modifier = Modifier, viewModel: ReviewViewModel, onBackClick: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState()
    val isBottomSheetVisible = remember { mutableStateOf(false) }
    val activity = LocalContext.current as Activity

    val isReview = viewModel.state.value.isOrdered

    val message = viewModel.state.value.messageModel

    val rating = viewModel.state.value.ratingModel

    val reviews = viewModel.state.value.review?.collectAsLazyPagingItems()

    LaunchedEffect(message) {
        if (message != null) {
            showToastMessage(message.message, activity)
            viewModel.onEvent(ReviewEvent.CLearMessage)
        }
    }

    val pagingState = reviews?.let { handleReviewPaging(reviews = it) }
    Scaffold(
        topBar = { CustomReviewTopAppBar(onBackClick = { onBackClick() }) },
        floatingActionButton = {
            if (isReview != null) {
                if (isReview.isOrdered) {
                    CreateReview(
                        sheetState = sheetState,
                        bottomSheetState = isBottomSheetVisible,
                        onClick = {
                            isBottomSheetVisible.value = true
                        },
                        viewModel = viewModel
                    )
                }
            }
        },
        containerColor = Color.Transparent,
        modifier = modifier.fillMaxSize()
    ) {
        val top = it.calculateTopPadding()
        if (pagingState == PagingState.INITIAL_LOADING) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                CustomMultiColoredProgressBar()
            }
        } else Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = top + 20.dp)
        ) {
            if (rating != null) {
                CustomReviewStatSection(rating = rating)
            }

            Spacer(modifier = Modifier.height(10.dp))
            if (rating != null) if (reviews != null) {
                CustomReviewCheckSection(noOfReviews = reviews.itemCount)
            }

            if (reviews != null) {
                if (pagingState != null) {
                    CustomReviewPagingScreen(pagingState = pagingState, reviews = reviews)
                }
            }
            if (pagingState == PagingState.SUCCESS) {
                if (reviews.itemCount == 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = top),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No ReviewFound",
                            fontSize = 26.sp,
                            color = ButtonColor,
                            fontWeight = FontWeight.W600
                        )
                    }
                }
            }
        }
    }
}
