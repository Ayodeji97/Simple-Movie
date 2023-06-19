package com.example.movie.business.repository

import com.example.movie.business.datasource.cache.cachesource.GetMoviesCacheSource
import com.example.movie.business.datasource.remote.remotesource.GetMoviesRemoteSource
import com.example.movie.business.util.DummyData
import com.example.movie.business.util.TestConstants.SEARCH_QUERY
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import com.example.movie.business.utils.Result

class GetMoviesRepositoryTest {

    private lateinit var getMoviesRemoteSource: GetMoviesRemoteSource
    private lateinit var getMoviesCacheSource: GetMoviesCacheSource

    @Before
    fun setup() {
        getMoviesRemoteSource = mock(GetMoviesRemoteSource::class.java)
        getMoviesCacheSource = mock(GetMoviesCacheSource::class.java)
    }

    @Test
    fun `check that calling get search result return list of movies`() = runBlocking {
        Mockito.`when`(getMoviesRemoteSource.getMovies(SEARCH_QUERY)).thenReturn(
            Result.Success(DummyData.searchResultDto)
        )

        val response = getMoviesRemoteSource.getMovies(SEARCH_QUERY)
        when (response) {
            is Result.Success -> {
                assertThat(response.data?.results).isNotEmpty()
                assertThat(response.data?.results?.size).isGreaterThan(0)
            }
            else -> {}
        }
    }

    @Test
    fun `check that calling get search result return empty list when search result is not found`() =
        runBlocking {
            Mockito.`when`(getMoviesRemoteSource.getMovies(SEARCH_QUERY)).thenReturn(
                Result.Success(DummyData.searchResultEmptyDto)
            )

            val response = getMoviesRemoteSource.getMovies(SEARCH_QUERY)
            when (response) {
                is Result.Success -> {
                    assertThat(response.data?.results?.size).isEqualTo(0)
                    assertThat(response.data?.results).isEmpty()
                }
                else -> {}
            }
        }

    @Test
    fun `check that calling get search result return throw an exception when there is an error`() =
        runBlocking {
            Mockito.`when`(getMoviesRemoteSource.getMovies(SEARCH_QUERY)).thenReturn(
                Result.Error(DummyData.exception.message ?: "")
            )

            val response = getMoviesRemoteSource.getMovies(SEARCH_QUERY)
            when (response) {
                is Result.Error -> {
                    assertThat(response.errorMessage).isNotEmpty()
                    assertThat(response.errorMessage).isEqualTo(DummyData.exception.message)
                }
                else -> {}
            }
        }

    @Test
    fun `check that calling get search result return the correct data`() = runBlocking {
        Mockito.`when`(getMoviesRemoteSource.getMovies(SEARCH_QUERY)).thenReturn(
            Result.Success(DummyData.searchResultDto)
        )

        val response = getMoviesRemoteSource.getMovies(SEARCH_QUERY)
        when (response) {
            is Result.Success -> {
                assertThat(response.data?.results?.size).isGreaterThan(0)
                assertThat(response.data?.results).isNotEmpty()
                assertThat(response.data?.results?.first()?.title)
                    .isEqualTo(DummyData.searchResultDto.results.first().title)
                assertThat(response.data?.results?.first()?.overview)
                    .isEqualTo(DummyData.searchResultDto.results.first().overview)
            }
            else -> {}
        }
    }
}
