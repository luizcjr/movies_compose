package com.example.movies.presentation.activity.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.MainCoroutineRule
import com.example.movies.domain.usecase.GetPopularMoviesUseCase
import com.example.movies.presentation.activity.model.Movies
import com.example.movies.presentation.activity.viewmodel.MainViewModelTestMock.moviesList
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val useCase = mock<GetPopularMoviesUseCase>()
    private val moviesObserver = mock<Observer<Movies>>()
    private val loadingObserver = mock<Observer<Boolean>>()
    private val errorObserver = mock<Observer<Boolean>>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(useCase)
        viewModel.movies.observeForever(moviesObserver)
        viewModel.loading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when observe live data, then call get movies, then return movies list`() =
        runBlocking {
            whenever(useCase.get()).doReturn(moviesList)
            viewModel.getMovies()
            verify(errorObserver).onChanged(false)
            verify(loadingObserver).onChanged(true)
            verify(moviesObserver).onChanged(Movies(moviesList))
            verify(loadingObserver).onChanged(false)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when observe live data, then call get movies, then return exception`() =
        runBlocking {
            whenever(useCase.get()).doThrow(IOException())
            viewModel.getMovies()
            verify(loadingObserver).onChanged(true)
            verify(errorObserver).onChanged(true)
            verify(loadingObserver).onChanged(false)
        }
}