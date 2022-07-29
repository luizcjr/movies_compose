package com.example.movies.domain.usecase

import com.example.movies.domain.model.Movie

interface GetPopularMoviesUseCase {
    suspend fun get(): List<Movie>
}