package com.example.fstore.domain.usecase.bag_usecase

data class BagUseCase(
    val createBag: CreateBag,
    val fetchBags: FetchBags,
    val fetchPrice: FetchPrice,
    val deleteBag: DeleteBag,
    val updateQuantity: UpdateQuantity
)
