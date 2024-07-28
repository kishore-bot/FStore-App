package com.example.fstore.presentation.bag

import androidx.paging.PagingData
import com.example.fstore.domain.model.BagPriceModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Bag
import com.example.fstore.domain.model.send.CreateFavModel
import kotlinx.coroutines.flow.Flow

data class BagState(
    val bags: Flow<PagingData<Bag>>? = null,
    val messages: MessageModel? = null,
    val price: Flow<BagPriceModel>? = null,
    val id: Int? = null,
    val fav: CreateFavModel? = null,
    val quantity: Bag? = null,
)
