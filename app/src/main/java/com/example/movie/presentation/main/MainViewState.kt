package com.example.movie.presentation.main

import com.example.movie.business.domain.model.Movie


data class MainViewState(
    val isLoading: Boolean = false,
    val searchResult: List<Movie>? = null,
    val error: String = ""
)
