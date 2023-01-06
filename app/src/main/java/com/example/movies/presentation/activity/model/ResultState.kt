package com.example.movies.presentation.activity.model

sealed class ResultState<out T : Any> {
    object Loading : ResultState<Nothing>()
    data class Success<out T : Any>(val result: T) : ResultState<T>()
    data class Error(val error: Throwable) : ResultState<Nothing>()
}
