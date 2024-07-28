package com.example.fstore.domain.usecase.bag_usecase

import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.repo.BagRepo

class DeleteBag(
    private val bagRepo: BagRepo
) {
    suspend operator fun invoke(id: Int): MessageModel {
        return bagRepo.delete(id)
    }
}