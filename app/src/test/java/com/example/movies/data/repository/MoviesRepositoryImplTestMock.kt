package com.example.movies.data.repository

import com.example.movies.data.network.response.DataContainerResponse
import com.example.movies.data.network.response.MoviesResponse

object MoviesRepositoryImplTestMock {

    private val movieResponse1 = MoviesResponse(
        1,
        "poster_path",
        "overview",
        listOf(28, 12),
        "original title",
        "title",
        9.8f
    )

    private val movieResponse2 = MoviesResponse(
        2,
        "poster_path",
        "overview 2",
        listOf(80, 99),
        "original title 2",
        "title 2",
        5.6f
    )

    val dataContainerResponse = DataContainerResponse(
        1,
        10,
        2,
        listOf(
            movieResponse1, movieResponse2
        )
    )
}