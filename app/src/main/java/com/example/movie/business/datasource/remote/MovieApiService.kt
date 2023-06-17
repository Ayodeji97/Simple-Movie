package com.engie.eea_tech_interview.business.datasource.remote

import com.engie.eea_tech_interview.business.datasource.remote.model.GenreResultDto
import com.engie.eea_tech_interview.business.datasource.remote.model.SearchResultDto
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
