package com.example.fstore.presentation.favorite

import androidx.paging.PagingData
import com.example.fstore.domain.model.send.CreateBagModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Favorite
import kotlinx.coroutines.flow.Flow

data class FavoriteState(
    val favorites: Flow<PagingData<Favorite>>? = null,
    val messages: MessageModel? = null,
    val id: Int? = null,
    val bag: CreateBagModel? = null
)
