package com.example.fstore.domain.repo

import androidx.paging.PagingData
import com.example.fstore.domain.model.BagPriceModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Bag
import com.example.fstore.domain.model.send.BagQuantityModel
import com.example.fstore.domain.model.send.CreateBagModel
import kotlinx.coroutines.flow.Flow

interface BagRepo {
    suspend fun createBag(bag: CreateBagModel): MessageModel
    suspend fun getBags(): Flow<PagingData<Bag>>
    suspend fun getPrice(): Flow<BagPriceModel>
    suspend fun delete(id: Int): MessageModel
    suspend fun updateQuantity(quantity: BagQuantityModel): MessageModel
}