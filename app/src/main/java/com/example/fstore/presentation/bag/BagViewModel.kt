package com.example.fstore.presentation.bag

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.model.send.BagQuantityModel
import com.example.fstore.domain.usecase.bag_usecase.BagUseCase
import com.example.fstore.domain.usecase.favorite_usercase.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BagViewModel @Inject constructor(
    private val bagUseCase: BagUseCase,
    private val favoriteUseCase: FavoriteUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(BagState())
    val state: MutableState<BagState> = _state

    fun onEvent(event: BagEvents) {
        when (event) {
            is BagEvents.FetchBags -> {
                getBags()
            }

            is BagEvents.FetchPrice -> {
                fetchPrice()
            }

            is BagEvents.UploadId -> {
                _state.value = _state.value.copy(id = event.id)
            }

            is BagEvents.ClearMessage -> {
                _state.value = _state.value.copy(messages = null)
            }

            is BagEvents.DeleteBag -> {
                deleteFavorite()
            }

            is BagEvents.UploadFav -> {
                _state.value = _state.value.copy(fav = event.fav)
            }

            is BagEvents.CreateFav -> {
                postFavorite()
            }

            BagEvents.UploadBag -> {
                upDateQuantity()
            }

            is BagEvents.UpdateQuantity -> {
                _state.value = _state.value.copy(quantity = event.bag)
            }
        }
    }

    private fun getBags() {
        viewModelScope.launch {
            val bagFlow = bagUseCase.fetchBags()
            _state.value = _state.value.copy(bags = bagFlow)
        }
    }

    private fun fetchPrice() {
        viewModelScope.launch {
            val price = bagUseCase.fetchPrice()
            _state.value = _state.value.copy(price = price)
        }
    }

    private fun deleteFavorite() {
        val id = _state.value.id
        viewModelScope.launch {
            val message = id?.let { bagUseCase.deleteBag(it) }
            getBags()
            fetchPrice()
            _state.value = _state.value.copy(messages = message)
        }
    }

    private fun postFavorite() {
        val favorite = _state.value.fav
        if (favorite != null) {
            viewModelScope.launch {
                val message = favoriteUseCase.postFavorite(favorite)
                _state.value = _state.value.copy(messages = message)
            }
        }
    }

    private fun upDateQuantity() {
        val quantity = _state.value.quantity?.let {
            BagQuantityModel(
                id = it.id, quantity = it.quantity
            )
        }
        viewModelScope.launch {
            val message = quantity?.let { bagUseCase.updateQuantity(it) }
            getBags()
            fetchPrice()
            _state.value = _state.value.copy(messages = message)
        }
    }

}
