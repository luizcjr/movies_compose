package com.example.movies.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movies.presentation.activity.model.Movies
import com.example.movies.presentation.activity.viewmodel.MainViewModel
import com.example.movies.presentation.components.HorizontalPagerWithOffsetTransition
import com.example.movies.presentation.theme.MediumBlack
import com.example.movies.presentation.theme.cardName

@Composable
fun MovieScreen(
    loadingState: State<Boolean?>,
    errorState: State<Boolean?>,
    moviesState: State<Movies?>,
    viewModel: MainViewModel,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MediumBlack)
    ) {
        val (
            loadingView,
            errorView,
            popularLabel,
            imageSlider
        ) = createRefs()

        if (loadingState.value == true) {
            LoadingView(modifier = Modifier
                .fillMaxSize()
                .constrainAs(loadingView) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                })
        }

        if (errorState.value == true) {
            ErrorItem(
                message = "Ocorreu um erro, tente novamente!",
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(loadingView) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            ) { viewModel.getMovies() }
        }

        moviesState.value?.let { movies ->


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