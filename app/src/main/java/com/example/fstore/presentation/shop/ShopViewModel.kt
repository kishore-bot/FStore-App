package com.example.fstore.presentation.shop

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
class ShopViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(ShopState())
    val state: State<ShopState> = _state

    fun onEvent(event: ShopEvent) {
        when (event) {
            ShopEvent.FetchBrands -> {
                getBrands()
            }

            is ShopEvent.UploadQuery -> {
                _state.value = _state.value.copy(query = event.query)
            }

            ShopEvent.ClearQuery -> {
                _state.value = _state.value.copy(query = null)
            }

            ShopEvent.FetchProducts -> {
                fetchProducts()
            }

            ShopEvent.FetchCategories -> {
                fetchCategories()
            }
        }
    }

    private fun getBrands() {
        val mainCategory = _state.value.query?.mainCategory
        viewModelScope.launch {
            val brands = mainCategory?.let { productUseCase.fetchBrands(it) }
            _state.value = _state.value.copy(brands = brands)
        }
    }

    private fun fetchCategories() {
        val mainCategory = _state.value.query?.mainCategory
        val gender = _state.value.query?.gender
        viewModelScope.launch {
            val category = mainCategory?.let { main ->
                gender?.let { gender ->
                    productUseCase.fetchCategory(mainCategory = main, gender = gender)
                }
            }
            _state.value = _state.value.copy(categories = category)
        }
    }

    private fun fetchProducts() {
        val query = _state.value.query ?: ProductQuery()
        viewModelScope.launch {
            val productsFlow = productUseCase.getProducts(query = query)
            _state.value = _state.value.copy(products = productsFlow)
        }
    }
}
