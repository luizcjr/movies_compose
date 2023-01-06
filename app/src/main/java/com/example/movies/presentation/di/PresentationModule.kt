package com.example.movies.presentation.di

import com.example.movies.presentation.activity.presenter.MainPresenter
import com.example.movies.presentation.activity.presenter.MainPresenterImpl
import com.example.movies.presentation.activity.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule() + presenterModule())
    }

    private fun presenterModule(): Module {
        return module {
            factory<MainPresenter> { MainPresenterImpl() }
        }
    }

    private fun viewModelModule(): Module {
        return module {
            viewModel { MainViewModel(get(), get()) }
        }
    }
}