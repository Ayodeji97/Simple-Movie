package com.example.movie.presentation.detail

import com.example.movie.business.domain.model.Movie

data class DetailViewState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
)
