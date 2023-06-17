package com.engie.eea_tech_interview.presentation.main

sealed class MainViewEvent {
    data class GetSearchResult(
        val searchQuery: String
    ) : MainViewEvent()
}
