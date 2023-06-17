package com.example.movie.business.datasource.remote.remotesource


import com.example.movie.business.datasource.remote.model.SearchResultDto
import com.example.movie.business.utils.Result

interface GetMoviesRemoteSource {
    suspend fun getMovies(searchQuery: String): Result<SearchResultDto>
}
