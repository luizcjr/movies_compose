package com.example.movies.data.repository

import com.example.movies.data.network.service.MoviesService
import com.example.movies.data.repository.MoviesRepositoryImplTestMock.dataContainerResponse
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class MoviesRepositoryImplTest {

    private val service = mock<MoviesService>()
    private val repository = MoviesRepositoryImpl(service)

    @Test
    fun `given repository, when call get popular, then return data`() = runBlocking {
        whenever(service.getPopular()).doReturn(Response.success(dataContainerResponse))
        val result = repository.getPopular()
        assertEquals(dataContainerResponse, result)
    }

    @Test(expected = Exception::class)
    fun `given repository, when call get popular, then exception`() = runBlocking {
        whenever(service.getPopular()).doThrow(IOException())
        verify(repository.getPopular())
        Unit
    }
}