package com.example.fstore.presentation.reviews

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.order_usercase.OrderUseCase
import com.example.fstore.domain.usecase.review_UseCase.ReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewUseCase: ReviewUseCase, private val orderUseCase: OrderUseCase
) : ViewModel() {

    private var _state = mutableStateOf(ReviewState())
    val state: State<ReviewState> = _state

    fun onEvent(event: ReviewEvent) {
        when (event) {
            is ReviewEvent.ReviewProduct -> {
                reviewAProduct()
            }

            is ReviewEvent.UploadId -> {
                _state.value = _state.value.copy(id = event.id)
            }

            is ReviewEvent.IsOrderedCheck -> isOrderedCheck()

            is ReviewEvent.UploadReview -> {
                _state.value = _state.value.copy(createReviewModel = event.review)
            }

            is ReviewEvent.CLearMessage -> {
                _state.value = _state.value.copy(messageModel = null)
            }

            is ReviewEvent.FetchRating -> fetchRating()
            is ReviewEvent.FetchReview -> fetchReview()
        }
    }

    private fun isOrderedCheck() {
        val id = _state.value.id
        viewModelScope.launch {
            val ordered = id?.let { orderUseCase.isOrdered(it) }
            _state.value = _state.value.copy(isOrdered = ordered)
        }
    }

    private fun reviewAProduct() {
        val review = _state.value.createReviewModel
        viewModelScope.launch {
            val message = review?.let { reviewUseCase.reviewProduct(review = it) }
            _state.value = _state.value.copy(messageModel = message)
        }
    }

    private fun fetchRating() {
        val id = _state.value.id
        viewModelScope.launch {
            val rating = id?.let { reviewUseCase.fetchRating(id = it) }
            _state.value = _state.value.copy(ratingModel = rating)
        }
    }

    private fun fetchReview() {
        val id = _state.value.id
        viewModelScope.launch {
            val review = id?.let { reviewUseCase.fetchReview(id = it) }
            _state.value = _state.value.copy(review = review)
        }
    }
}
