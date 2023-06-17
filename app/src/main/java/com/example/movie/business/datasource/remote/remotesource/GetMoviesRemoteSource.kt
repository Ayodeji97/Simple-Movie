package com.engie.eea_tech_interview.business.datasource.remote.remotesource

import com.engie.eea_tech_interview.business.datasource.remote.model.SearchResultDto
import com.engie.eea_tech_interview.business.utils.Result

interface GetMoviesRemoteSource {
    suspend fun getMovies(searchQuery: String): Result<SearchResultDto>
}
