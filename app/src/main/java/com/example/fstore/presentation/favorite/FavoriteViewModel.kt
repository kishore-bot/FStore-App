package com.example.fstore.presentation.favorite


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fstore.domain.usecase.bag_usecase.BagUseCase
import com.example.fstore.domain.usecase.favorite_usercase.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase, private val bagUseCase: BagUseCase
) : ViewModel() {

    private var _state = mutableStateOf(FavoriteState())
    val state: State<FavoriteState> = _state

    fun onEvent(event: FavoriteEvents) {
        when (event) {
            is FavoriteEvents.FetchFavorites -> {
                getFavorites()
            }

            is FavoriteEvents.UploadId -> {
                _state.value = _state.value.copy(id = event.id)
            }

            is FavoriteEvents.DeleteFavorite -> {
                deleteFavorite()
            }

            is FavoriteEvents.ClearMessage -> {
                _state.value = _state.value.copy(messages = null)
            }

            is FavoriteEvents.UploadBag -> {
                _state.value = _state.value.copy(bag = event.bag)
            }

            is FavoriteEvents.CreateBag -> {
                createBag()
            }

        }
    }

    private fun deleteFavorite() {
        val id = _state.value.id
        viewModelScope.launch {
            if (id != null) {
                val message = favoriteUseCase.deleteFavorite(id = id)
                getFavorites()
                _state.value = _state.value.copy(messages = message)
            }
        }

    }

    private fun getFavorites() {
        viewModelScope.launch {
            val favoriteFlow = favoriteUseCase.fetchFavorites()
            _state.value = _state.value.copy(favorites = favoriteFlow)
        }
    }


    private fun createBag() {
        val bag = _state.value.bag
        if (bag != null) {
            viewModelScope.launch {
                val message = bagUseCase.createBag(bag)
                _state.value = _state.value.copy(messages = message)
            }
        }
    }

}
