package com.example.movie.presentation.main

sealed class MainViewEvent {
    data class GetSearchResult(
        val searchQuery: String
    ) : MainViewEvent()
}
