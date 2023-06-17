package com.example.movie.business.domain.usecase


import com.engie.eea_tech_interview.business.repository.GetMoviesRepository
import com.example.movie.business.datasource.cache.model.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesFromDbUseCase @Inject constructor(
    private val getMoviesRepository: GetMoviesRepository
) {
    suspend operator fun invoke(): Flow<List<MovieEntity>> =
        getMoviesRepository.getMoviesFromDb()
}
