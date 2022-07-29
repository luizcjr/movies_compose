package com.example.movies.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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