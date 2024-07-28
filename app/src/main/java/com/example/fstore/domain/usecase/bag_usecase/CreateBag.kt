package com.example.fstore.domain.usecase.bag_usecase


import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.send.CreateBagModel
import com.example.fstore.domain.repo.BagRepo

class CreateBag(
    private val bagRepo: BagRepo
) {
    suspend operator fun invoke(bag: CreateBagModel): MessageModel {
        return bagRepo.createBag(bag)
    }
}