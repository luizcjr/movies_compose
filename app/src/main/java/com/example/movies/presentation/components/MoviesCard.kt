package com.example.movies.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movies.R
import com.example.movies.domain.model.Movie
import com.example.movies.presentation.screen.LoadingItem
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.cardName
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MoviesCard(
    page: Int,
    movies: List<Movie>? = null
) {
    ConstraintLayout {
        val (
            image,
            constraintContent
        ) = createRefs()

        movies?.let {
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

@Preview(showBackground = true)
@Composable
fun MoviesCardPreview() {
    MoviesTheme {
        MoviesCard(
            page = 0,
            movies = listOf(
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