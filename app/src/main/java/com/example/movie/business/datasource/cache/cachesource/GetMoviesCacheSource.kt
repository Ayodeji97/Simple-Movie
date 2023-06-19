package com.example.movie.business.datasource.cache.cachesource


import com.example.movie.business.datasource.cache.dao.MovieDao
import com.example.movie.business.datasource.cache.model.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesCacheSource @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun saveMovies(movies: List<MovieEntity>) =
        movieDao.insert(movies)

    fun getMovies(): Flow<List<MovieEntity>> =
        movieDao.getMovies()
}
