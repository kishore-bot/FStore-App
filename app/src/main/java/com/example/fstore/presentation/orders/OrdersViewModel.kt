package com.example.fstore.presentation.orders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.order_usercase.OrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderUseCase: OrderUseCase
) : ViewModel() {
    private val _state = mutableStateOf(OrderState())
    val state: State<OrderState> = _state

    fun fetchOrders() {
        viewModelScope.launch {
            val orders = orderUseCase.fetchOrders()
            _state.value = _state.value.copy(orders = orders)
        }
    }
}

