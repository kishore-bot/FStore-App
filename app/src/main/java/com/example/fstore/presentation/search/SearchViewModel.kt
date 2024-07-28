package com.example.fstore.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.model.send.ProductQuery
import com.example.fstore.domain.usecase.product_usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> {
               fetchProducts()
            }

            is SearchEvent.UploadSearchQuery -> {
                _state.value = _state.value.copy(query = event.query)
            }
        }
    }

    private fun fetchProducts() {
        val searchString = _state.value.query
        val query = ProductQuery(category = searchString)
        viewModelScope.launch {
            val productsFlow = productUseCase.getProducts(query = query)
            _state.value = _state.value.copy(products = productsFlow)
        }
    }
}
