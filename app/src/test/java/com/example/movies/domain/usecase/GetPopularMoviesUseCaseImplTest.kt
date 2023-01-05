package com.example.movies.domain.usecase

import com.example.movies.data.repository.MoviesRepository
import com.example.movies.domain.usecase.GetPopularMoviesUseCaseImplTestMock.dataContainerResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.Assert
import org.junit.Test

class GetPopularMoviesUseCaseImplTest {

    private val repository = mock<MoviesRepository>()
    private val useCase = GetPopularMoviesUseCaseImpl(repository)

    @Test
    fun `given response, when call get popular movies, then return movies`() = runBlocking {
        whenever(repository.getPopular()).doReturn(dataContainerResponse)
        val result = useCase.get()
        Assert.assertNotNull(result.first())
    }

    @Test(expected = Exception::class)
    fun `given response, when call get popular movies, then return exception`() = runBlocking {
        whenever(repository.getPopular()).doThrow(IOException())
        useCase.get()
        Unit
    }
}