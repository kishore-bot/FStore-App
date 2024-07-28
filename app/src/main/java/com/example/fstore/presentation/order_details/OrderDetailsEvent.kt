package com.example.fstore.presentation.order_details

sealed class OrderDetailsEvent {
    data class UpdateId(val id: Int) : OrderDetailsEvent()
    data object FetchData : OrderDetailsEvent()
}