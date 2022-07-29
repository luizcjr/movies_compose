package com.example.movies.data.mapper

import com.example.movies.data.network.response.MoviesResponse
import com.example.movies.domain.model.GenresEnum
import com.example.movies.domain.model.Movie

object MoviesMapper : AbstractMapper<MoviesResponse, Movie>() {

    override fun toEntity(response: MoviesResponse) =
        Movie(
            imageUrl = "https://image.tmdb.org/t/p/w500${response.posterPath}",
            genres = mapGenresList(listOf(28)).let { if (it.size > 1) it.subList(0, 2) else it },
            average = response.voteAverage.toString()
        )

    private fun mapGenresList(genreIds: List<Int>): List<String> = mutableListOf<String>()
        .apply {
            genreIds.map { genres ->
                when (genres) {
                    GenresEnum.ACTION.genreId -> add(GenresEnum.ACTION.genre)
                    GenresEnum.ADVENTURE.genreId -> add(GenresEnum.ADVENTURE.genre)
                    GenresEnum.ANIMATION.genreId -> add(GenresEnum.ANIMATION.genre)
                    GenresEnum.COMEDY.genreId -> add(GenresEnum.COMEDY.genre)
                    GenresEnum.CRIME.genreId -> add(GenresEnum.CRIME.genre)
                    GenresEnum.DOCUMENTARY.genreId -> add(GenresEnum.DOCUMENTARY.genre)
                    GenresEnum.DRAMA.genreId -> add(GenresEnum.DRAMA.genre)
                    GenresEnum.FAMILY.genreId -> add(GenresEnum.FAMILY.genre)
                    GenresEnum.FANTASY.genreId -> add(GenresEnum.FANTASY.genre)
                    GenresEnum.HISTORY.genreId -> add(GenresEnum.HISTORY.genre)
                    GenresEnum.TERROR.genreId -> add(GenresEnum.TERROR.genre)
                    GenresEnum.MUSIC.genreId -> add(GenresEnum.MUSIC.genre)
                    GenresEnum.MYSTERY.genreId -> add(GenresEnum.MYSTERY.genre)
                    GenresEnum.NOVEL.genreId -> add(GenresEnum.NOVEL.genre)
                    GenresEnum.SCI_FI.genreId -> add(GenresEnum.SCI_FI.genre)
                    GenresEnum.TV.genreId -> add(GenresEnum.TV.genre)
                    GenresEnum.THRILLER.genreId -> add(GenresEnum.THRILLER.genre)
                    GenresEnum.WAR.genreId -> add(GenresEnum.WAR.genre)
                    else -> add(GenresEnum.WESTERN.genre)
                }
            }
        }
}