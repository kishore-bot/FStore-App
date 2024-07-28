package com.example.fstore.presentation.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.address_usecase.AddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ProfileState())
    val state: MutableState<ProfileState> = _state

    fun fetchProfile() {
        viewModelScope.launch {
            val profile = addressUseCase.fetchProfile()
            _state.value = _state.value.copy(profileModel = profile)
        }

    }
}