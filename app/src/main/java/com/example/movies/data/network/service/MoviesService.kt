package com.example.movies.data.network.service

import com.example.movies.data.network.response.DataContainerResponse
import com.example.movies.data.network.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopular(): Response<DataContainerResponse<MoviesResponse>>
}