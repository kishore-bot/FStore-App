package com.example.fstore.domain.model

import com.example.fstore.domain.model.rec_sub.Favorite

data class FavoriteModel(
    val favorites: List<Favorite>,
    val total: Int
)