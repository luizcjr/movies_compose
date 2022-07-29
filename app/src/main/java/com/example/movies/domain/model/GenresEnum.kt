package com.example.movies.domain.model

enum class GenresEnum(val genreId: Int, val genre: String) {
    ACTION(28, "Ação"),
    ADVENTURE(12, "Aventura"),
    ANIMATION(16, "Animação"),
    COMEDY(35, "Comédia"),
    CRIME(80, "Crime"),
    DOCUMENTARY(99, "Documentário"),
    DRAMA(18, "Drama"),
    FAMILY(10751, "Família"),
    FANTASY(14, "Fantasia"),
    HISTORY(36, "História"),
    TERROR(27, "Terror"),
    MUSIC(10402, "Música"),
    MYSTERY(9648, "Mistério"),
    NOVEL(10749, "Romance"),
    SCI_FI(878, "Ficção Científica"),
    TV(10770, "Cinema TV"),
    THRILLER(53, "Thriller"),
    WAR(10752, "Guerra"),
    WESTERN(37, "Faroeste"),
}