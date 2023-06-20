package com.example.movie.business.repository


import android.util.Log
import com.example.movie.business.datasource.remote.remotesource.GetMoviesRemoteSource
import com.example.movie.business.datasource.cache.cachesource.GetMoviesCacheSource
import com.example.movie.business.datasource.cache.model.MovieEntity
import com.example.movie.business.domain.model.Movie
import com.example.movie.business.utils.mapper.cachemapper.MovieCacheMapper
import com.example.movie.business.utils.mapper.remotemapper.MovieDtoMapper
import com.example.movie.business.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesRepositoryImpl @Inject constructor(
    private val getMoviesRemoteSource: GetMoviesRemoteSource,
    private val getMoviesCacheSource: GetMoviesCacheSource,
    private val movieDtoMapper: MovieDtoMapper,
    private val movieCacheMapper: MovieCacheMapper
) : GetMoviesRepository {
    override suspend fun getMovies(searchQuery: String): Flow<Result<List<Movie>>> =
        flow {
            when (val response = getMoviesRemoteSource.getMovies(searchQuery)) {
                is Result.Success -> {
                    response.data?.let {
                        val movies = movieDtoMapper.transformToEntity(it.results)
                        if (movies.isNotEmpty()) {
                            getMoviesCacheSource.saveMovies(movies)
                        }
                        emit(
                            Result.Success(
                                movieCacheMapper.transformToDomain(movies)
                            )
                        )
                    }
                }
                is Result.Error -> {
                    emit(Result.Error(response.errorMessage))
                }
            }
        }

    override suspend fun getMoviesFromDb(): Flow<List<MovieEntity>> =
        getMoviesCacheSource.getMovies()

    override suspend fun getMovie(movieId: Int): MovieEntity =
        getMoviesCacheSource.getMovie(movieId)
}
