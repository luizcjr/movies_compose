package com.example.movies.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.movies.domain.model.Movie
import com.example.movies.presentation.theme.MoviesTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithOffsetTransition(
    size: Int,
    movies: List<Movie>,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        count = size.let { if (it > 5) 5 else it },
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 0.dp),
        modifier = modifier
    ) { page ->
        Card(
            Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            MoviesCard(page = page, movies = movies)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesTheme {
        HorizontalPagerWithOffsetTransition(
            size = 5,
            movies = listOf(
                Movie(
                    imageUrl = "https://image.tmdb.org/t/p/w500/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                    average = "6.7",
                    genres = listOf("Ação", "Aventura"),
                    title = "Thor: Amor e Trovão"
                ),
                Movie(
                    imageUrl = "https://image.tmdb.org/t/p/w500/7qeiCNSmzrkcEyIWi8sIcsjrOyW.jpg",
                    average = "7.1",
                    genres = listOf("Aventura", "Ação"),
                    title = "Jurassic World: Domínio"
                ),
                Movie(
                    imageUrl = "https://image.tmdb.org/t/p/w500/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                    average = "6.7",
                    genres = listOf("Ação", "Aventura"),
                    title = "Thor: Amor e Trovão"
                ),
                Movie(
                    imageUrl = "https://image.tmdb.org/t/p/w500/7qeiCNSmzrkcEyIWi8sIcsjrOyW.jpg",
                    average = "7.1",
                    genres = listOf("Aventura", "Ação"),
                    title = "Jurassic World: Domínio"
                ),
                Movie(
                    imageUrl = "https://image.tmdb.org/t/p/w500/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                    average = "6.7",
                    genres = listOf("Ação", "Aventura"),
                    title = "Thor: Amor e Trovão"
                )
            )
        )
    }
}