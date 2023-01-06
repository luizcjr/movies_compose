package com.example.movies.presentation.activity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.usecase.GetPopularMoviesUseCase
import com.example.movies.presentation.activity.model.ModelView
import com.example.movies.presentation.activity.model.Movies
import com.example.movies.presentation.activity.model.ResultState
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(
    private val popularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _moviesState = MutableLiveData<ResultState<Movies>>()
    val moviesState: LiveData<ResultState<Movies>>
        get() = _moviesState

    private val _movies = MutableLiveData<ModelView<Movies>>()
    val movies: LiveData<ModelView<Movies>>
        get() = _movies

    fun getMovies() {
        _moviesState.postValue(ResultState.Loading)
        viewModelScope.launch {
            try {
                _moviesState.postValue(ResultState.Success(Movies(popularMoviesUseCase.get())))
            } catch (e: IOException) {
                _moviesState.postValue(ResultState.Error(e))
            }
        }
    }

    fun present(state: ResultState<Movies>) = when (state) {
        is ResultState.Error -> {
            _movies.postValue(
                ModelView(
                    loading = false,
                    error = true,
                    errorMessage = state.error.message
                )
            )
        }
        is ResultState.Success -> {
            _movies.postValue(
                ModelView(loading = false, error = false, result = state.result)
            )
        }
        else -> {
            _movies.postValue(
                ModelView(loading = true, error = false)
            )
        }
    }
}