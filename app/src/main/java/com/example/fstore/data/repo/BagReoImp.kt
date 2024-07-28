package com.example.fstore.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.fstore.data.paging.BagPaging
import com.example.fstore.data.remote.BagApi
import com.example.fstore.domain.model.BagPriceModel
import com.example.fstore.domain.model.send.CreateBagModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.rec_sub.Bag
import com.example.fstore.domain.model.send.BagQuantityModel
import com.example.fstore.domain.repo.BagRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BagReoImp @Inject constructor(
    private val bagApi: BagApi
) : BagRepo {
    override suspend fun createBag(bag: CreateBagModel): MessageModel {
        val message = bagApi.createBag(bag)
        return message
    }

    override suspend fun getBags(): Flow<PagingData<Bag>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                BagPaging(bagApi = bagApi)
            },
        ).flow
    }

    override suspend fun getPrice(): Flow<BagPriceModel> {
        return flow { emit(bagApi.getPrice()) }
    }

    override suspend fun delete(id: Int): MessageModel {
        val message = bagApi.deleteBag(id)
        return message
    }

    override suspend fun updateQuantity(quantity: BagQuantityModel): MessageModel {
        val message = bagApi.updateQuantity(quantity)
        return message
    }

}