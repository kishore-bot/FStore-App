package com.example.fstore.presentation.home

sealed class HomeEvent {
    data object FetchProducts : HomeEvent()
}