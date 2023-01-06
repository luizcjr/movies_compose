package com.example.movies.presentation.activity.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.MainCoroutineRule
import com.example.movies.presentation.activity.model.ModelView
import com.example.movies.presentation.activity.model.Movies
import com.example.movies.presentation.activity.model.ResultState
import com.example.movies.presentation.activity.viewmodel.MainViewModelTestMock
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class MainPresenterImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val presenter = MainPresenterImpl()
    private val moviesObserver = mock<Observer<ModelView<Movies>>>()

    @Before
    fun setup() {
        presenter.getObservable().observeForever(moviesObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test present when state is success`() =
        runBlocking {
            val result = Movies(
                MainViewModelTestMock.moviesList
            )
            presenter.present(ResultState.Success(result))
            verify(moviesObserver).onChanged(
                ModelView(loading = false, error = false, result = result)
            )
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test present when state is loading`() =
        runBlocking {
            presenter.present(ResultState.Loading)
            verify(moviesObserver).onChanged(ModelView(loading = true, error = false))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test present when state is error`() =
        runBlocking {
            val exception = IOException()
            presenter.present(ResultState.Error(exception))
            verify(moviesObserver).onChanged(
                ModelView(
                    loading = false,
                    error = true,
                    errorMessage = exception.message
                )
            )
        }
}