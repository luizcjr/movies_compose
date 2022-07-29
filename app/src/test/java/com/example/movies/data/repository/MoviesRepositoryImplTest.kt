package com.example.movies.data.repository

import com.example.movies.data.network.service.MoviesService
import com.example.movies.data.repository.MoviesRepositoryImplTestMock.dataContainerResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesRepositoryImplTest {

    private val service = mock<MoviesService>()
    private val repository = MoviesRepositoryImpl(service)

    @Test
    fun `given repository, when call get popular, then return data`() = runBlocking {
        whenever(service.getPopular()).doReturn(dataContainerResponse)
        val result = repository.getPopular()
        assertEquals(dataContainerResponse, result)
    }
}