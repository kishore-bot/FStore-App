package com.example.fstore.presentation.address.add_address

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.address_usecase.AddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAddressViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase
) : ViewModel() {

    private var _state = mutableStateOf(AddAddressState())
    val state: MutableState<AddAddressState> = _state

    fun onEvent(event: AddAddressEvent) {
        when (event) {
            is AddAddressEvent.CreateAddress -> {
                createAddress()
            }

            is AddAddressEvent.UpdateAddress -> {
                _state.value = _state.value.copy(address = event.address)
            }
        }
    }

    private fun createAddress() {
        val address = _state.value.address
        viewModelScope.launch {
            val message = address?.let { addressUseCase.createAddress(it) }
            _state.value = _state.value.copy(message = message)
        }
    }
}
