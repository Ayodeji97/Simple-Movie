package com.example.movie.business.datasource.remote.remotesource

import com.example.movie.business.datasource.remote.MovieApiService
import com.example.movie.business.datasource.remote.model.SearchResultDto
import com.example.movie.business.utils.Constants.MOVIE_API_KEY
import com.example.movie.di.daggerhilt.dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import com.example.movie.business.utils.Result

class GetMoviesRemoteSourceImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetMoviesRemoteSource {
    override suspend fun getMovies(searchQuery: String): Result<SearchResultDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                val apiResponse = movieApiService.getMovies(MOVIE_API_KEY, searchQuery)
                if (apiResponse.isSuccessful) {
                    val movies = apiResponse.body()
                    Result.Success(movies)
                } else {
                    val errorMessageObject = apiResponse.errorBody()?.string()
                    apiResponse.errorBody()?.close()
                    val errorMessage = errorMessageObject?.let {
                        JSONObject(it).getString("errors")
                    }
                    Result.Error(errorMessage ?: "Something went wrong")
                }
            } catch (httpException: HttpException) {
                Result.Error(errorMessage = httpException.message())
            } catch (ioException: IOException) {
                Result.Error(
                    errorMessage = ioException.message ?: "Please check your network connection"
                )
            }
        }
}
