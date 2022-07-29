package com.example.movies.data.network.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_title")
    val originalTitle: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Float
)
