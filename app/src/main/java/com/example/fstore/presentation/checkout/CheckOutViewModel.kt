package com.example.fstore.presentation.checkout


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.address_usecase.AddressUseCase
import com.example.fstore.domain.usecase.order_usercase.OrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase,
    private val orderUseCase: OrderUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(CheckOutState())
    val state: State<CheckOutState> = _state

    fun fetchPrimAryAddress() {
        viewModelScope.launch {
            val addresses = addressUseCase.fetchPrimaryAddress()
            _state.value = _state.value.copy(address = addresses)
        }
    }

    fun placeOrder() {
        viewModelScope.launch {
            val messageModel = orderUseCase.placeOrder()
            _state.value = _state.value.copy(messageModel = messageModel)
        }
    }

}


