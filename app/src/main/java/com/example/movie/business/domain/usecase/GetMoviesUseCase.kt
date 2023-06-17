package com.example.movie.business.domain.usecase


import com.example.movie.business.domain.model.Movie
import com.example.movie.business.repository.GetMoviesRepository
import com.example.movie.business.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val getMoviesRepository: GetMoviesRepository
) {
    suspend operator fun invoke(searchQuery: String): Flow<Result<List<Movie>>> =
        getMoviesRepository.getMovies(searchQuery)
}
