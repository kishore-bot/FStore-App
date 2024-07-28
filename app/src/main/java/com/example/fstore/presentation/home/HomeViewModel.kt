package com.example.fstore.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.product_usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.FetchProducts -> getProducts()
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            val products = productUseCase.fetchHome()
            _state.value = _state.value.copy(products = products)
        }
    }
}
