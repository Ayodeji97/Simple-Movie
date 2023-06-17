package com.example.movie.business.datasource.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResultDto(
    @Json(name = "genres") val genreDtos: List<GenreDto>
)
