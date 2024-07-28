package com.example.fstore.domain.usecase.bag_usecase

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.send.BagQuantityModel
import com.example.fstore.domain.repo.BagRepo

class UpdateQuantity(
    private val bagRepo: BagRepo
) {
    suspend operator fun invoke(bag: BagQuantityModel): MessageModel {
        return bagRepo.updateQuantity(bag)
    }
}