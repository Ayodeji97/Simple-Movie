package com.example.movie.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.business.domain.model.Movie
import com.example.movie.business.domain.usecase.GetMoviesFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMoviesFromDbUseCase: GetMoviesFromDbUseCase
): ViewModel() {

    private var _getMovieDetailViewState = MutableStateFlow(DetailViewState())
    val getMovieDetailViewState = _getMovieDetailViewState.asStateFlow()

    fun onTriggeredEvent(event: DetailViewEvent) {
        when(event) {
            is DetailViewEvent.GetMovieDetail -> getMovie(event.movieId)
        }
    }


    private fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _getMovieDetailViewState.value.let {state->
                _getMovieDetailViewState.value = state.copy(isLoading = true)
                val movie = getMoviesFromDbUseCase(movieId)
                _getMovieDetailViewState.value = state.copy(isLoading = false, movie = movie)
            }
        }
    }
}