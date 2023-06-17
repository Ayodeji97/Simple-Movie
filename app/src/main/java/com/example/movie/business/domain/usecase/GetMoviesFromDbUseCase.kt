package com.engie.eea_tech_interview.business.domain.usecase

import com.engie.eea_tech_interview.business.datasource.cache.model.MovieEntity
import com.engie.eea_tech_interview.business.repository.GetMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesFromDbUseCase @Inject constructor(
    private val getMoviesRepository: GetMoviesRepository
) {
    suspend operator fun invoke(): Flow<List<MovieEntity>> =
        getMoviesRepository.getMoviesFromDb()
}
