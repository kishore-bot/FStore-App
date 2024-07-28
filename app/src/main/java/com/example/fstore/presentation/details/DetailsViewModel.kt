package com.example.fstore.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.model.send.ProductQuery
import com.example.fstore.domain.usecase.bag_usecase.BagUseCase
import com.example.fstore.domain.usecase.favorite_usercase.FavoriteUseCase
import com.example.fstore.domain.usecase.product_usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val bagUseCase: BagUseCase
) : ViewModel() {

    private var _state = mutableStateOf(DetailsState())
    val state: State<DetailsState> = _state

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpdateId -> {
                _state.value = _state.value.copy(id = event.id)
            }

            is DetailsEvent.FetchDetails -> {
                fetchDetails()
            }

            is DetailsEvent.GetSimilarProducts -> {
                getSimilarProducts()
            }

            is DetailsEvent.UpdateFavoriteModel -> {
                _state.value = _state.value.copy(favorites = event.favorite)
            }

            is DetailsEvent.PostFavorite -> {
                postFavorite()
            }

            is DetailsEvent.ClearMessage -> {
                _state.value = _state.value.copy(messageModel = null)
            }

            is DetailsEvent.UploadBag -> {
                _state.value = _state.value.copy(bag = event.bag)
            }

            is DetailsEvent.CreateBag -> {
                createBag()
            }

            is DetailsEvent.UploadQuery -> {
                _state.value = _state.value.copy(query = event.query)
            }
        }
    }

    private fun fetchDetails() {
        val id = _state.value.id
        if (id != null) {
            viewModelScope.launch {
                val details = productUseCase.fetchDetails(id)
                _state.value = _state.value.copy(details = details)
            }
        }
    }

    private fun getSimilarProducts() {
        val query = _state.value.query ?: ProductQuery()
        viewModelScope.launch {
            val productsFlow = query.let { productUseCase.getProducts(query = it) }
            _state.value = _state.value.copy(products = productsFlow)
        }
    }

    private fun postFavorite() {
        val favorite = _state.value.favorites
        if (favorite != null) {
            viewModelScope.launch {
                val message = favoriteUseCase.postFavorite(favorite)
                _state.value = _state.value.copy(messageModel = message)
            }
        }
    }

    private fun createBag() {
        val bag = _state.value.bag
        if (bag != null) {
            viewModelScope.launch {
                val message = bagUseCase.createBag(bag)
                _state.value = _state.value.copy(messageModel = message)
            }
        }
    }
}
