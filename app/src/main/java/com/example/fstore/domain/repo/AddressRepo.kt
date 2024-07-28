package com.example.fstore.domain.repo

import com.example.fstore.domain.model.AddressesModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.ProfileModel
import com.example.fstore.domain.model.rec_sub.Addresses
import com.example.fstore.domain.model.send.CreateAddressModel

interface AddressRepo {
    suspend fun createAddress(address: CreateAddressModel): MessageModel
    suspend fun fetchAddress(): AddressesModel
    suspend fun deleteAddress(id: Int): MessageModel
    suspend fun changeAddress(id: Int): MessageModel
    suspend fun fetchPrimaryAddress(): Addresses?
    suspend fun fetchProfile(): ProfileModel
}