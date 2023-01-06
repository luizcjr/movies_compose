package com.example.movies.presentation.activity.model

data class ModelView<T>(
    val loading: Boolean,
    val error: Boolean,
    val errorMessage: String? = null,
    val throwable: Throwable? = null,
    val result: T? = null
)
