package com.example.movies.domain.usecase

import com.example.movies.data.mapper.MoviesMapper
import com.example.movies.data.repository.MoviesRepository
import com.example.movies.domain.model.Movie

class GetPopularMoviesUseCaseImpl(
    private val repository: MoviesRepository
) : GetPopularMoviesUseCase {

    override suspend fun get(): List<Movie> =
        repository.getPopular().results.map { MoviesMapper.toEntity(it) }
}