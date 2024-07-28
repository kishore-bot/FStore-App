package com.example.fstore.data.remote

import com.example.fstore.domain.model.AddressesModel
import com.example.fstore.domain.model.MessageModel
import com.example.fstore.domain.model.ProfileModel
import com.example.fstore.domain.model.rec_sub.Addresses
import com.example.fstore.domain.model.send.CreateAddressModel
import com.example.fstore.utils.Constants.ADDRESS_URL
import com.example.fstore.utils.Constants.CHANGE_ADDRESS_URL
import com.example.fstore.utils.Constants.FETCH_PRIMARY_ADDRESS
import com.example.fstore.utils.Constants.PROFILE_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressApi {
    @POST(ADDRESS_URL)
    suspend fun createAddress(
        @Body address: CreateAddressModel
    ): MessageModel

    @GET(ADDRESS_URL)
    suspend fun fetchAddress(): AddressesModel

    @DELETE("$ADDRESS_URL/{id}")
    suspend fun deleteAddress(@Path("id") id: Int): MessageModel

    @POST("$CHANGE_ADDRESS_URL/{id}")
    suspend fun changeAddress(@Path("id") id: Int): MessageModel

    @GET(FETCH_PRIMARY_ADDRESS)
    suspend fun fetchPrimaryAddress(): Addresses? = null

    @GET(PROFILE_URL)
    suspend fun fetchProfile(): ProfileModel
}