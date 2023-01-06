package com.example.movies.presentation.activity.presenter

import androidx.lifecycle.LiveData
import com.example.movies.presentation.activity.model.ModelView
import com.example.movies.presentation.activity.model.Movies
import com.example.movies.presentation.activity.model.ResultState

interface MainPresenter {
    fun present(state: ResultState<Movies>)
    fun getObservable() : LiveData<ModelView<Movies>>
}