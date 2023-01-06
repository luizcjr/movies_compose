package com.example.movies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movies.presentation.activity.viewmodel.MainViewModel
import com.example.movies.presentation.screen.FavoriteScreen
import com.example.movies.presentation.screen.MovieScreen
import com.example.movies.presentation.screen.SerieScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel? = null,
    lifecycleOwner: LifecycleOwner? = null
) {

    NavHost(navController = navController, startDestination = BottomBarScreen.Movies.route) {
        composable(BottomBarScreen.Movies.route) {
            viewModel?.let { view ->
                view.getMovies()
                lifecycleOwner?.let {
                    view.moviesState.observe(it) { movies ->
                        view.present(movies)
                    }
                }

                MovieScreen(
                    state = view.movies.observeAsState(),
                    viewModel = view
                )
            }
        }

        composable(BottomBarScreen.Series.route) {
            SerieScreen()
        }

        composable(BottomBarScreen.Favorites.route) {
            FavoriteScreen()
        }
    }
}