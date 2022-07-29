package com.example.movies.presentation.application

import android.app.Application
import com.example.movies.data.di.DataModules
import com.example.movies.domain.di.DomainModules
import com.example.movies.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApplication)
            androidLogger()
            DataModules.load()
            DomainModules.load()
            PresentationModule.load()
        }
    }
}