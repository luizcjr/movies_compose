package com.example.movies.data.repository

import com.example.movies.data.network.response.DataContainerResponse
import com.example.movies.data.network.response.MoviesResponse
import com.example.movies.data.network.service.MoviesService

class MoviesRepositoryImpl(
    private val service: MoviesService
) : MoviesRepository {

    override suspend fun getPopular(): DataContainerResponse<MoviesResponse> = service.getPopular()
}