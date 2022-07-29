package com.example.movies.presentation.activity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.usecase.GetPopularMoviesUseCase
import com.example.movies.presentation.activity.model.Movies
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(
    private val popularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<Movies>()
    val movies: LiveData<Movies>
        get() = _movies

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    fun getMovies() {
        viewModelScope.launch {
            try {
                _loading.postValue(true)
                _error.postValue(false)
                _movies.postValue(Movies(popularMoviesUseCase.get()))
            } catch (e: IOException) {
                _error.postValue(true)
            } finally {
                _loading.postValue(false)
            }
        }
    }
}