package com.example.fstore.presentation.main_activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.local_usecase.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _result = MutableStateFlow(false)
    val result: StateFlow<Boolean> = _result

    init {
        checkToken()
    }

    private fun checkToken() {
        viewModelScope.launch {
            val token = appEntryUseCases.readAppEntry().firstOrNull()?:""
            _result.value = token.isBlank()
            _isLoading.value = false
        }
    }
}
