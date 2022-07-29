package com.example.movies.domain.di

import com.example.movies.domain.usecase.GetPopularMoviesUseCase
import com.example.movies.domain.usecase.GetPopularMoviesUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModules {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            single<GetPopularMoviesUseCase> { GetPopularMoviesUseCaseImpl(get()) }
        }
    }
}