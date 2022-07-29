package com.example.movies.domain.usecase

import com.example.movies.data.mapper.MoviesMapper
import com.example.movies.data.repository.MoviesRepository
import com.example.movies.domain.model.Movie
import java.io.IOException
import kotlin.jvm.Throws

class GetPopularMoviesUseCaseImpl(
    private val repository: MoviesRepository
) : GetPopularMoviesUseCase {

    @Throws(IOException::class)
    override suspend fun get(): List<Movie> =
        repository.getPopular().results.map { MoviesMapper.toEntity(it) }
}