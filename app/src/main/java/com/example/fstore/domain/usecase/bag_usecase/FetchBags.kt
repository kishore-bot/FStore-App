package com.example.fstore.domain.usecase.bag_usecase

import androidx.paging.PagingData
import com.example.fstore.domain.model.rec_sub.Bag
import com.example.fstore.domain.repo.BagRepo
import kotlinx.coroutines.flow.Flow

class FetchBags(
    private val bagRepo: BagRepo
) {
    suspend operator fun invoke(): Flow<PagingData<Bag>> {
        return bagRepo.getBags()
    }
}