package com.example.movie.business.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class SearchResultDto(
    @SerializedName("results") val results: List<MovieDto>
)
