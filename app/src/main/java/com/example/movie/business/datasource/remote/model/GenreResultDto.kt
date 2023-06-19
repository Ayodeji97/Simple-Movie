package com.example.movie.business.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class GenreResultDto(
    @SerializedName("genres") val genreDtos: List<GenreDto>
)
