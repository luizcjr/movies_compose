package com.example.movies.data.repository

import com.example.movies.data.network.response.DataContainerResponse
import com.example.movies.data.network.response.MoviesResponse
import com.example.movies.data.network.service.MoviesService
import java.io.IOException

class MoviesRepositoryImpl(
    private val service: MoviesService
) : MoviesRepository {

    override suspend fun getPopular(): DataContainerResponse<MoviesResponse> = try {
        val response = service.getPopular()
        if (response.isSuccessful) {
            response.body()?.let { moviesResponse ->
                return moviesResponse
            } ?: run {
                throw IOException("")
            }
        } else {
            throw IOException("")
        }
    } catch (e: Exception) {
        throw e
    }
}