package com.example.movies.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movies.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val fontFamily = FontFamily(
    Font(R.font.apercu_pro_medium),
    Font(R.font.apercu_pro_regular)
)

val cardName = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    color = Color.White,
    fontFamily = fontFamily
)

val cardGenres = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Thin,
    color = Color.White,
    fontFamily = fontFamily
)

val cardAverage = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Light,
    color = Color.White,
    fontFamily = fontFamily
)