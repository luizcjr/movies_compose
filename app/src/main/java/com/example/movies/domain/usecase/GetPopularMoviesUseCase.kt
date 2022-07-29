package com.example.movies.domain.usecase

import com.example.movies.domain.model.Movie
import java.io.IOException
import kotlin.jvm.Throws

interface GetPopularMoviesUseCase {

    @Throws(IOException::class)
    suspend fun get(): List<Movie>
}