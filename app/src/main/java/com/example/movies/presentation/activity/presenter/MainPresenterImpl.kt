package com.example.movies.presentation.activity.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.presentation.activity.model.ModelView
import com.example.movies.presentation.activity.model.Movies
import com.example.movies.presentation.activity.model.ResultState

class MainPresenterImpl : MainPresenter {

    private val _movies = MutableLiveData<ModelView<Movies>>()
    private val movies: LiveData<ModelView<Movies>>
        get() = _movies

    override fun present(state: ResultState<Movies>) = when (state) {
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

    override fun getObservable(): LiveData<ModelView<Movies>> = movies
}