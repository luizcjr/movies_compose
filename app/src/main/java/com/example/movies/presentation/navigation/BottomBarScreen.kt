package com.example.movies.presentation.navigation

import com.example.movies.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Movies : BottomBarScreen(
        "movies",
        "Filmes",
        R.drawable.ic_baseline_movie_24
    )

    object Series : BottomBarScreen(
        "series",
        "Séries",
        R.drawable.ic_baseline_local_movies_24
    )

    object Favorites : BottomBarScreen(
        "favorites",
        "Favoritos",
        R.drawable.ic_baseline_favorite_24
    )
}
