package com.example.fstore.presentation.order_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.model.send.BagQuantityModel
import com.example.fstore.domain.repo.OrderRepo
import com.example.fstore.presentation.bag.BagEvents
import com.example.fstore.presentation.bag.BagState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val orderRepo: OrderRepo
) : ViewModel() {

    private var _state = mutableStateOf(OrderDetailsState())
    val state: MutableState<OrderDetailsState> = _state

    fun onEvent(event: OrderDetailsEvent) {
        when (event) {
            is OrderDetailsEvent.FetchData -> {
                fetchOrderDetails()
            }
            is OrderDetailsEvent.UpdateId -> {
                _state.value = _state.value.copy(id = event.id)
            }
        }
    }

    private fun fetchOrderDetails() {
        val id = _state.value.id
        viewModelScope.launch {
            val orderDetails = id?.let { orderRepo.fetchOrderById(it) }
            _state.value = _state.value.copy(orderDetails = orderDetails)
        }
    }

}
