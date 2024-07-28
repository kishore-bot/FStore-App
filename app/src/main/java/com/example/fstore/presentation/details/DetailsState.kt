package com.example.fstore.presentation.details

import androidx.paging.PagingData
import com.example.fstore.domain.model.send.CreateBagModel
import com.example.fstore.domain.model.send.CreateFavModel
import com.example.fstore.domain.model.DetailsModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.domain.model.send.ProductQuery
import kotlinx.coroutines.flow.Flow

data class DetailsState(
    val id: Int? = null,
    val details: DetailsModel? = null,
    val products: Flow<PagingData<Product>>? = null,
    val favorites: CreateFavModel? = null,
    val messageModel: MessageModel? = null,
    val bag: CreateBagModel? = null,
    val query: ProductQuery? = null,
)