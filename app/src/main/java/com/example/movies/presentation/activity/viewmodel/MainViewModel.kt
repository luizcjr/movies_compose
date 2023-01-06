package com.example.movies.presentation.activity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.usecase.GetPopularMoviesUseCase
import com.example.movies.presentation.activity.model.Movies
import com.example.movies.presentation.activity.model.ResultState
import com.example.movies.presentation.activity.presenter.MainPresenter
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    val mainPresenter: MainPresenter
) : ViewModel() {

    private val _moviesState = MutableLiveData<ResultState<Movies>>()
    val moviesState: LiveData<ResultState<Movies>>
        get() = _moviesState

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

    fun present(state: ResultState<Movies>) = mainPresenter.present(state)
}