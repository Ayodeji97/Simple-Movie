package com.example.movie.business.domain.usecase



import com.example.movie.business.datasource.cache.model.MovieEntity
import com.example.movie.business.repository.GetMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesFromDbUseCase @Inject constructor(
    private val getMoviesRepository: GetMoviesRepository
) {
    suspend operator fun invoke(): Flow<List<MovieEntity>> =
        getMoviesRepository.getMoviesFromDb()
}
