package com.example.fstore.presentation.address

sealed class AddressEvent {
    data object FetchAddress : AddressEvent()
    data class UploadId(val id: Int) : AddressEvent()
    data object DeletedAddress : AddressEvent()
    data object ChangeAddress : AddressEvent()
}