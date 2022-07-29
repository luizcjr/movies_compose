package com.example.movies.domain.usecase

import com.example.movies.data.repository.MoviesRepository
import com.example.movies.domain.usecase.GetPopularMoviesUseCaseImplTestMock.dataContainerResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
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
}