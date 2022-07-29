package com.example.movies.data.mapper

abstract class AbstractMapper<D, E> {
    abstract fun toEntity(response: D): E

    open fun toEntityList(responseList: List<D>): List<E> {
        return responseList.map { toEntity(it) }
    }
}