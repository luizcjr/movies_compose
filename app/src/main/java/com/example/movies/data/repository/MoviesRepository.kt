package com.example.movies.data.repository

import com.example.movies.data.network.response.DataContainerResponse
import com.example.movies.data.network.response.MoviesResponse

interface MoviesRepository {
    suspend fun getPopular(): DataContainerResponse<MoviesResponse>
}