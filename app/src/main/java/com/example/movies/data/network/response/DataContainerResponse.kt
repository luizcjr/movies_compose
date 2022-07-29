package com.example.movies.data.network.response

import com.google.gson.annotations.SerializedName

data class DataContainerResponse<T>(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<T>
)