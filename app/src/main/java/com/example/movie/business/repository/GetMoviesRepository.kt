package com.example.movie.business.repository


import com.example.movie.business.datasource.cache.model.MovieEntity
import com.example.movie.business.domain.model.Movie
import com.example.movie.business.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetMoviesRepository {
    suspend fun getMovies(searchQuery: String): Flow<Result<List<Movie>>>

    suspend fun getMoviesFromDb(): Flow<List<MovieEntity>>

    suspend fun getMovie(movieId: Int): MovieEntity
}
