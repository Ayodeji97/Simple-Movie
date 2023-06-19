package com.example.movie.presentation.main

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movie.business.util.DummyData
import com.example.movie.business.domain.usecase.GetMoviesFromDbUseCase
import com.example.movie.business.domain.usecase.GetMoviesUseCase
import com.example.movie.business.repository.GetMoviesRepository
import com.example.movie.business.util.MainCoroutineRule
import com.example.movie.business.util.TestConstants.SEARCH_QUERY
import com.example.movie.business.utils.mapper.cachemapper.MovieCacheMapper
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config
import com.example.movie.business.utils.Result

@RunWith(MockitoJUnitRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var getMoviesRepository: GetMoviesRepository

    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var getMoviesFromDbUseCase: GetMoviesFromDbUseCase

    private lateinit var mainViewModel: MainViewModel

    private lateinit var movieCacheMapper: MovieCacheMapper

    /**
     * This rules all related arch component background Job in the same thread
     * */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        getMoviesRepository = mock(GetMoviesRepository::class.java)
        getMoviesUseCase = GetMoviesUseCase(getMoviesRepository)
        getMoviesFromDbUseCase = GetMoviesFromDbUseCase(getMoviesRepository)
        movieCacheMapper = MovieCacheMapper()
        mainViewModel = MainViewModel(getMoviesUseCase, getMoviesFromDbUseCase, movieCacheMapper)
    }

    @Test
    fun `check that call to useCase return search result`() = runBlocking {
        `when`(getMoviesUseCase.invoke(SEARCH_QUERY)).thenReturn(
            flowOf(Result.Success(listOf(DummyData.movie)))
        )

        mainViewModel.onTriggeredEvent(MainViewEvent.GetSearchResult(SEARCH_QUERY))

        Truth.assertThat(
            mainViewModel.getSearchResultViewState.value
        ).isEqualTo(MainViewState().copy(isLoading = false, searchResult = listOf(DummyData.movie)))
    }

    @Test
    fun `check that call to useCase return empty list when search query is not found`() =
        runBlocking {
            `when`(getMoviesUseCase.invoke(SEARCH_QUERY)).thenReturn(
                flowOf(Result.Success(listOf()))
            )

            mainViewModel.onTriggeredEvent(MainViewEvent.GetSearchResult(SEARCH_QUERY))

            Truth.assertThat(
                mainViewModel.getSearchResultViewState.value
            ).isEqualTo(MainViewState().copy(isLoading = false, searchResult = listOf()))
        }

    @Test
    fun `check that call to useCase return error exception and return error message when there is an exception`() =
        runBlocking {
            `when`(getMoviesUseCase.invoke(SEARCH_QUERY)).thenReturn(
                flowOf(Result.Error(DummyData.exception.message ?: ""))
            )

            mainViewModel.onTriggeredEvent(MainViewEvent.GetSearchResult(SEARCH_QUERY))

            Truth.assertThat(
                mainViewModel.getSearchResultViewState.value
            ).isEqualTo(
                MainViewState().copy(
                    isLoading = false,
                    error = DummyData.exception.message ?: ""
                )
            )
        }
}
