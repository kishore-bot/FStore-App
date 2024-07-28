package com.example.fstore.presentation.search

sealed class SearchEvent {
    data object Search : SearchEvent()
    data class UploadSearchQuery(val query: String) : SearchEvent()
}