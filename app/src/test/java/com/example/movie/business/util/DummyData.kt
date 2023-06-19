package com.example.movie.business.util

import com.example.movie.business.datasource.cache.model.MovieEntity
import com.example.movie.business.datasource.remote.model.MovieDto
import com.example.movie.business.datasource.remote.model.SearchResultDto
import com.example.movie.business.domain.model.Movie


object DummyData {

    val error_text = "Exception thrown"
    val exception = Exception(error_text)

    val movieDto = MovieDto(
        id = 2,
        posterPath = "www.sdfv.jpeg",
        overview = "This is a small overview for testing",
        releaseDate = "2022-12-45",
        originalTitle = "James",
        genreIds = listOf(),
        mediaType = "audio",
        originalLanguage = "important",
        title = "James",
        voteCount = 3,
        hasVideo = false,
    )

    val movieDto2 = MovieDto(
        id = 10,
        posterPath = "www.sdfv.jpeg",
        overview = "This is a second small overview for testing",
        releaseDate = "2022-12-45",
        originalTitle = "James",
        genreIds = listOf(),
        mediaType = "audio",
        originalLanguage = "important",
        title = "Elon",
        voteCount = 3,
        hasVideo = true,
    )

    val movieEntity = MovieEntity(
        id = 2,
        posterPath = "www.sdfv.jpeg",
        overview = "This is a small overview for testing",
        releaseDate = "2022-12-45",
        originalTitle = "James",
        mediaType = "audio",
        originalLanguage = "important",
        title = "James",
        voteCount = 3,
    )

    val movieEntity2 = MovieEntity(
        id = 10,
        posterPath = "www.sdfv.jpeg",
        overview = "This is a second small overview for testing",
        releaseDate = "2022-12-45",
        originalTitle = "James",
        mediaType = "audio",
        originalLanguage = "important",
        title = "Elon",
        voteCount = 3,
    )

    val searchResultDto = SearchResultDto(
        results = listOf(movieDto)
    )

    val searchResultEmptyDto = SearchResultDto(
        results = listOf()
    )

    val movie = Movie(
        id = 2,
        posterPath = "www.sdfv.jpeg",
        overview = "This is a small overview for testing",
        releaseDate = "2022-12-45",
        originalTitle = "James",
        mediaType = "audio",
        originalLanguage = "important",
        title = "James",
        voteCount = 3,
    )
}
