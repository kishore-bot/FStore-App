package com.example.fstore.domain.usecase.address_usecase

data class AddressUseCase(
    val createAddress: CreateAddress,
    val fetchAddress: FetchAddress,
    val deletedAddress: DeletedAddress,
    val changeAddress: ChangeAddress,
    val fetchPrimaryAddress: FetchPrimaryAddress,
    val fetchProfile: FetchProfile
)
