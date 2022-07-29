package com.example.movies.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movies.presentation.screen.FavoriteScreen
import com.example.movies.presentation.screen.MovieScreen
import com.example.movies.presentation.screen.SerieScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {

    NavHost(navController = navController, startDestination = BottomBarScreen.Movies.route) {
        composable(BottomBarScreen.Movies.route) {
            MovieScreen()
        }

        composable(BottomBarScreen.Series.route) {
            SerieScreen()
        }

        composable(BottomBarScreen.Favorites.route) {
            FavoriteScreen()
        }
    }
}