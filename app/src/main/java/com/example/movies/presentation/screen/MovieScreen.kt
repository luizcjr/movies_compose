package com.example.movies.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movies.presentation.activity.model.ModelView
import com.example.movies.presentation.activity.model.Movies
import com.example.movies.presentation.activity.viewmodel.MainViewModel
import com.example.movies.presentation.components.HorizontalPagerWithOffsetTransition
import com.example.movies.presentation.theme.MediumBlack
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.cardName

@Composable
fun MovieScreen(
    viewModel: MainViewModel? = null,
    state: State<ModelView<Movies>?>? = null,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(MediumBlack)
    ) {
        val (
            loadingView,
            errorView,
            popularLabel,
            imageSlider
        ) = createRefs()

        state?.let {
            if (state.value?.loading == true) {
                LoadingView(modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(loadingView) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    })
            }

            if (state.value?.error == true) {
                ErrorItem(
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(errorView) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        },
                    message = "Ocorreu um erro, tente novamente!"
                ) { viewModel?.getMovies() }
            }

            state.value?.result?.let { movies ->
                Text(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth()
                        .constrainAs(popularLabel) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        },
                    text = "Popular",
                    color = Color.White,
                    style = cardName,
                    textAlign = TextAlign.Start
                )

                HorizontalPagerWithOffsetTransition(
                    size = movies.popular.size,
                    movies = movies.popular,
                    modifier = Modifier.constrainAs(imageSlider) {
                        top.linkTo(popularLabel.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview() {
    MoviesTheme {
        MovieScreen()
    }
}