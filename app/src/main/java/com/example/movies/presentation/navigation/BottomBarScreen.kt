package com.example.movies.presentation.navigation

import com.example.movies.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Movies : BottomBarScreen(
        route = "movies",
        title = "Filmes",
        icon = R.drawable.ic_baseline_movie_24
    )

    object Series : BottomBarScreen(
        route = "series",
        title = "Séries",
        icon = R.drawable.ic_baseline_local_movies_24
    )

    object Favorites : BottomBarScreen(
        route = "favorites",
        title = "Favoritos",
        icon = R.drawable.ic_baseline_favorite_24
    )
}
