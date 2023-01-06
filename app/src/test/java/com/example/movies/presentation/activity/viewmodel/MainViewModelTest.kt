package com.example.movies.presentation.activity.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.MainCoroutineRule
import com.example.movies.domain.usecase.GetPopularMoviesUseCase
import com.example.movies.presentation.activity.model.ModelView
import com.example.movies.presentation.activity.model.Movies
import com.example.movies.presentation.activity.model.ResultState
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
    private val moviesStateObserver = mock<Observer<ResultState<Movies>>>()
    private val moviesObserver = mock<Observer<ModelView<Movies>>>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(useCase)
        viewModel.movies.observeForever(moviesObserver)
        viewModel.moviesState.observeForever(moviesStateObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when observe live data, then call get movies, then return movies list`() =
        runBlocking {
            whenever(useCase.get()).doReturn(moviesList)
            viewModel.getMovies()
            verify(moviesStateObserver).onChanged(ResultState.Loading)
            verify(moviesStateObserver).onChanged(ResultState.Success(Movies(moviesList)))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when observe live data, then call get movies, then return exception`() =
        runBlocking {
            val exception = IOException()
            whenever(useCase.get()).doThrow(exception)
            viewModel.getMovies()
            verify(moviesStateObserver).onChanged(ResultState.Loading)
            verify(moviesStateObserver).onChanged(ResultState.Error(exception))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test present when state is success`() =
        runBlocking {
            val result = Movies(
                moviesList
            )
            viewModel.present(ResultState.Success(result))
            verify(moviesObserver).onChanged(
                ModelView(loading = false, error = false, result = result)
            )
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test present when state is loading`() =
        runBlocking {
            viewModel.present(ResultState.Loading)
            verify(moviesObserver).onChanged(ModelView(loading = true, error = false))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test present when state is error`() =
        runBlocking {
            val exception = IOException()
            viewModel.present(ResultState.Error(exception))
            verify(moviesObserver).onChanged(ModelView(loading = false, error = true, errorMessage = exception.message))
        }
}