package com.example.movies.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.movies.presentation.activity.viewmodel.MainViewModel
import com.example.movies.presentation.components.BottomBar
import com.example.movies.presentation.components.TopBarLayout
import com.example.movies.presentation.navigation.BottomNavGraph
import com.example.movies.presentation.theme.MoviesTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MoviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopBarLayout()
                        },
                        bottomBar = {
                            BottomBar(navController = navController)
                        }
                    ) {
                        BottomNavGraph(
                            navController = navController,
                            viewModel = viewModel,
                            paddingValues = it
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesTheme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(bottomBar = { BottomBar(navController = navController) }) {
                BottomNavGraph(navController = navController, viewModel = null, paddingValues = it)
            }
        }
    }
}