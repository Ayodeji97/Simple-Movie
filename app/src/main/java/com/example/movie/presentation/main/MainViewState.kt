package com.engie.eea_tech_interview.presentation.main

import com.engie.eea_tech_interview.business.domain.model.Movie

data class MainViewState(
    val isLoading: Boolean = false,
    val searchResult: List<Movie>? = null,
    val error: String = ""
)
