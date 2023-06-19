package com.example.movie.business.datasource.remote

import com.example.movie.business.datasource.remote.remotesource.GetMoviesRemoteSource
import com.example.movie.business.datasource.remote.remotesource.GetMoviesRemoteSourceImpl
import com.example.movie.business.util.MainCoroutineRule
import com.example.movie.business.util.TestConstants.API_KEY_TEST
import com.example.movie.business.util.TestConstants.MOVIE_JSON
import com.example.movie.business.util.TestConstants.OVERVIEW
import com.example.movie.business.util.TestConstants.TITLE
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetMovieRemoteSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private lateinit var movieApiService: MovieApiService
    private lateinit var getMoviesRemoteSource: GetMoviesRemoteSource

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/")
        movieApiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieApiService::class.java)

        getMoviesRemoteSource = GetMoviesRemoteSourceImpl(movieApiService, Dispatchers.Main)
    }

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `check that get searchResult method is successful`() = runBlocking {
        enqueueMockResponse(MOVIE_JSON)
        val responseSuccessful: Boolean =
            movieApiService.getMovies(API_KEY_TEST, "James").isSuccessful
        Truth.assertThat(responseSuccessful).isTrue()
    }

    @Test
    fun `check that get searchResult method does not return null`() = runBlocking {
        enqueueMockResponse(MOVIE_JSON)
        val response = movieApiService.getMovies(API_KEY_TEST, "James").body()
        assertThat(response).isNotNull()
    }

    @Test
    fun `check that get searchResult method return at least a corresponding correct data`() =
        runBlocking {
            enqueueMockResponse(MOVIE_JSON)
            val response = movieApiService.getMovies(API_KEY_TEST, "James").body()
            assertThat(response).isNotNull()
            assertThat(response?.results?.first()?.title).isEqualTo(TITLE)
            assertThat(response?.results?.first()?.overview).isEqualTo(OVERVIEW)
        }
}
