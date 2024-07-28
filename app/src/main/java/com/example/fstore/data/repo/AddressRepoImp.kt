package com.example.fstore.data.repo

import com.example.fstore.data.remote.AddressApi
import com.example.fstore.domain.model.AddressesModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.ProfileModel
import com.example.fstore.domain.model.rec_sub.Addresses
import com.example.fstore.domain.model.send.CreateAddressModel
import com.example.fstore.domain.repo.AddressRepo
import javax.inject.Inject

class AddressRepoImp @Inject constructor(
    private val addressApi: AddressApi
) : AddressRepo {
    override suspend fun createAddress(address: CreateAddressModel): MessageModel {
        return (addressApi.createAddress(address))
    }

    override suspend fun fetchAddress(): AddressesModel {
        return addressApi.fetchAddress()
    }

    override suspend fun deleteAddress(id: Int): MessageModel {
        return addressApi.deleteAddress(id)
    }

    override suspend fun changeAddress(id: Int): MessageModel {
        return addressApi.changeAddress(id)
    }

    override suspend fun fetchPrimaryAddress(): Addresses? {
        val response = addressApi.fetchPrimaryAddress()
        if (response != null) {
            if (response.name != null) return response
        }
        return null
    }

    override suspend fun fetchProfile(): ProfileModel {
        return addressApi.fetchProfile()
    }
}