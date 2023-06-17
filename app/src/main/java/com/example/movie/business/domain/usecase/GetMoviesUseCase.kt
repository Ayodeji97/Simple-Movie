package com.engie.eea_tech_interview.business.domain.usecase

import com.engie.eea_tech_interview.business.domain.model.Movie
import com.engie.eea_tech_interview.business.repository.GetMoviesRepository
import com.engie.eea_tech_interview.business.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val getMoviesRepository: GetMoviesRepository
) {
    suspend operator fun invoke(searchQuery: String): Flow<Result<List<Movie>>> =
        getMoviesRepository.getMovies(searchQuery)
}
