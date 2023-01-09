package com.example.movies.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.presentation.theme.Blue
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.cardGenres

@Composable
fun CategoriesItem(
    genre: String
) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(size = 30.dp))
            .background(color = Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(all = 8.dp),
            text = genre,
            color = Color.White,
            style = cardGenres,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesItemPreview() {
    MoviesTheme {
        CategoriesItem(genre = "Ação")
    }
}