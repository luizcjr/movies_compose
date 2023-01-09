package com.example.movies.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movies.R
import com.example.movies.domain.model.Movie
import com.example.movies.presentation.screen.LoadingItem
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.cardAverage
import com.example.movies.presentation.theme.cardName
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MoviesCard(
    movie: Movie? = null
) {
    ConstraintLayout(
        modifier = Modifier
            .width(800.dp)
    ) {
        val (
            image,
            constraintContent
        ) = createRefs()

        movie?.let {
            GlideImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                imageModel = movie.imageUrl ?: "",
                loading = {
                    LoadingItem()
                },
                failure = {
                    Text(text = "image request failed.")
                },
                contentScale = ContentScale.FillBounds
            )

            ConstraintLayout(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(constraintContent) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
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
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .constrainAs(textName) {
                            bottom.linkTo(categoryList.top)
                            start.linkTo(parent.start)
                            end.linkTo(textAverage.start)
                        },
                    text = movie.title,
                    color = Color.White,
                    style = cardName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .constrainAs(textAverage) {
                            top.linkTo(textName.top)
                            bottom.linkTo(textName.bottom)
                            end.linkTo(imageAverage.start)
                        },
                    text = movie.average,
                    color = Color.White,
                    style = cardAverage
                )

                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .constrainAs(imageAverage) {
                            top.linkTo(textAverage.top)
                            bottom.linkTo(textAverage.bottom)
                            end.linkTo(parent.end)
                        },
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    contentDescription = "Estrela",
                    tint = Color.Yellow
                )

                LazyRow(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .constrainAs(categoryList) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }) {
                    items(movie.genres.size) { movieIndex ->
                        CategoriesItem(genre = movie.genres[movieIndex])
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesCardPreview() {
    MoviesTheme {
        MoviesCard(
            movie =
            Movie(
                imageUrl = "https://image.tmdb.org/t/p/w500/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                average = "6.7",
                genres = listOf("Ação", "Aventura"),
                title = "Thor: Amor e Trovão"
            )
        )
    }
}