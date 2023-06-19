package com.example.movie.presentation.detail

sealed class DetailViewEvent {
    data class GetMovieDetail(
        val movieId: Int
    ): DetailViewEvent()
}
