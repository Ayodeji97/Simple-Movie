package com.example.movie.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.business.domain.usecase.GetMoviesFromDbUseCase
import com.example.movie.business.domain.usecase.GetMoviesUseCase
import com.example.movie.business.utils.Constants.DEFAULT_SEARCH_QUERY
import com.example.movie.business.utils.mapper.cachemapper.MovieCacheMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.movie.business.utils.Result

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMoviesFromDbUseCase: GetMoviesFromDbUseCase,
    private val movieCacheMapper: MovieCacheMapper
) : ViewModel() {

    private var _getSearchResultViewState = MutableStateFlow(MainViewState())
    val getSearchResultViewState = _getSearchResultViewState.asStateFlow()

    init {
        getSearchResultFromDb()
    }

    fun onTriggeredEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.GetSearchResult -> {
                getSearchResult(event.searchQuery)
            }
        }
    }

    private fun getSearchResult(searchQuery: String) {
        viewModelScope.launch {
            _getSearchResultViewState.value.let { state ->
                _getSearchResultViewState.value = state.copy(isLoading = true)
                getMoviesUseCase.invoke(searchQuery).collect {
                    when (it) {
                        is Result.Success -> {
                            _getSearchResultViewState.value =
                                state.copy(isLoading = false, searchResult = it.data)
                        }
                        is Result.Error -> {
                            _getSearchResultViewState.value =
                                state.copy(isLoading = false, error = it.errorMessage)
                        }
                    }
                }
            }
        }
    }

    private fun getSearchResultFromDb() {
        viewModelScope.launch {
            _getSearchResultViewState.value.let { state ->
                getMoviesFromDbUseCase.invoke().collect { movies ->
                    if (movies.isNullOrEmpty()) {
                        getSearchResult(DEFAULT_SEARCH_QUERY)
                    } else {
                        _getSearchResultViewState.value =
                            state.copy(searchResult = movieCacheMapper.transformToDomain(movies))
                    }
                }
            }
        }
    }
}
