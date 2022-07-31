package com.example.movies.presentation.activity.viewmodel

import com.example.movies.domain.model.Movie

object MainViewModelTestMock {

    private val movie1 = Movie(
        "image url",
        listOf("Ação", "Romance"),
        "9.8",
        "Thor"
    )

    private val movie2 = Movie(
        "image url",
        listOf("Documentário", "Drama"),
        "7.2",
        "Homem Aranha"
    )

    val moviesList = listOf(movie1, movie2)
}