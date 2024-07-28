package com.example.fstore.presentation.address

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.address_usecase.AddressUseCase
import com.example.fstore.domain.usecase.local_usecase.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase, private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private var _state = mutableStateOf(AddressState())
    val state: MutableState<AddressState> = _state

    fun onEvent(event: AddressEvent) {
        when (event) {
            AddressEvent.FetchAddress -> {
                fetchAddress()
            }

            AddressEvent.ChangeAddress -> {
                changeAddress()
            }

            AddressEvent.DeletedAddress -> {
                deleteAddress()
            }

            is AddressEvent.UploadId -> {
                _state.value = _state.value.copy(id = event.id)
            }
        }
    }

    private fun fetchAddress() {
        viewModelScope.launch {
            val addresses = addressUseCase.fetchAddress()
            _state.value = _state.value.copy(addressesModel = addresses)
        }
    }

    private fun changeAddress() {
        val id = _state.value.id
        viewModelScope.launch {
            val messageModel = id?.let { addressUseCase.changeAddress(it) }
            fetchAddress()
            _state.value = _state.value.copy(messageModel = messageModel)
        }
    }

    private fun deleteAddress() {
        val id = _state.value.id
        viewModelScope.launch {
            val messageModel = id?.let { addressUseCase.deletedAddress(it) }
            fetchAddress()
            _state.value = _state.value.copy(messageModel = messageModel)
        }
    }
}