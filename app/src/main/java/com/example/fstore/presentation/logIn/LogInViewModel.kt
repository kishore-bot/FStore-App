package com.example.fstore.presentation.logIn

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.auth_usecase.AuthUseCase
import com.example.fstore.domain.usecase.local_usecase.AppEntryUseCases
import com.example.fstore.presentation.register.RegisterEvent
import com.example.fstore.presentation.register.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authUseCase: AuthUseCase, private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    private var _state = mutableStateOf(LogInState())

    private val _result = MutableLiveData(false)
    val result: LiveData<Boolean> = _result

    fun onEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.UpdateAuthModel -> {
                _state.value = _state.value.copy(authModel = event.authModel)
            }

            is LogInEvent.LogIn -> {
                viewModelScope.launch {
                    userSignIn()
                }

            }
        }
    }

    private suspend fun userSignIn() {
        val credentials = _state.value.authModel!!
        viewModelScope.launch {
            authUseCase.logIn(credentials).collect {
                val token = it.token;
                appEntryUseCases.saveAppEntry(token)
                _result.value = true
            }
        }
    }
}