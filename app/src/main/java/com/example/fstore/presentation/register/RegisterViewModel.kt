package com.example.fstore.presentation.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.auth_usecase.AuthUseCase
import com.example.fstore.domain.usecase.local_usecase.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase, private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    private var _state = mutableStateOf(RegisterState())

    private val _result = MutableLiveData(false)
    val result: LiveData<Boolean> = _result

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.UpdateAuthModel -> {
                _state.value = _state.value.copy(authModel = event.authModel)
            }

            is RegisterEvent.Register -> {
                viewModelScope.launch {
                    userSignUp()
                }

            }
        }
    }

    private suspend fun userSignUp() {
        val credentials = _state.value.authModel!!
        viewModelScope.launch {
            authUseCase.register(credentials).collect {
                val token = it.token;
                appEntryUseCases.saveAppEntry(token)
                _result.value = true
            }
        }
    }
}