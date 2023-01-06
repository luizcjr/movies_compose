package com.example.movies.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movies.R
import com.example.movies.domain.model.Movie
import com.example.movies.presentation.screen.LoadingItem
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.cardName
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.skydoves.landscapist.glide.GlideImage
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
            ConstraintLayout {
                val (
                    image,
                    constraintContent
                ) = createRefs()

                GlideImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight()
                        .width(800.dp)
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    imageModel = movies[page].imageUrl ?: "",
                    loading = {
                        LoadingItem()
                    },
                    failure = {
                        Text(text = "image request failed.")
                    },
                    contentScale = ContentScale.FillHeight
                )

                ConstraintLayout(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .background(Color.Black)
                        .fillMaxWidth()
                        .wrapContentWidth()
                        .constrainAs(constraintContent) {
                            bottom.linkTo(image.bottom)
                            start.linkTo(image.start)
                            end.linkTo(image.end)
                        }
                ) {

                    val (
                        textName,
                        textAverage,
                        imageAverage,
                        categoryList
                    ) = createRefs()

                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .constrainAs(textName) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(textAverage.start)
                            },
                        text = movies[page].title,
                        color = Color.White,
                        style = cardName,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .constrainAs(textAverage) {
                                top.linkTo(parent.top)
                                end.linkTo(imageAverage.start)
                            },
                        text = movies[page].average,
                        color = Color.White,
                        style = cardName,
                        textAlign = TextAlign.Start
                    )

                    Icon(
                        modifier = Modifier
                            .constrainAs(imageAverage) {
                                top.linkTo(textAverage.top)
                                bottom.linkTo(textAverage.bottom)
                                end.linkTo(parent.end)
                            },
                        painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                        contentDescription = "Estrela",
                        tint = Color.Yellow
                    )
                }
            }
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