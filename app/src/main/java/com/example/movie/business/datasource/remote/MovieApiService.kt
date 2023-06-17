package com.example.movie.business.datasource.remote

import com.example.movie.business.datasource.remote.model.GenreResultDto
import com.example.movie.business.datasource.remote.model.SearchResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("search/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): Response<SearchResultDto>

    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String
    ): Response<GenreResultDto>
}
