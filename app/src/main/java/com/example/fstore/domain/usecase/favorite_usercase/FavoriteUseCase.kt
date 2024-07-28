package com.example.fstore.domain.usecase.favorite_usercase

data class FavoriteUseCase(
    val postFavorite: PostFavorite,
    val fetchFavorites: FetchFavorites,
    val deleteFavorite: DeleteFavorite
)
