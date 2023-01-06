package com.example.movies.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.cardName

@Composable
fun TopBarLayout() {
    TopAppBar(
        title = {
            Row {
                Text(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth(),
                    text = "Movies",
                    color = Color.White,
                    style = cardName,
                    textAlign = TextAlign.Start
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    MoviesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(topBar = { TopBarLayout() }) {

            }
        }
    }
}