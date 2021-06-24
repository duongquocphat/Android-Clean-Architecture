package com.shakutara.cleanarchitecture.features.list

import com.shakutara.cleanarchitecture.core.functional.Either
import com.shakutara.cleanarchitecture.core.interactor.UseCase
import com.shakutara.cleanarchitecture.features.MoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieListUseCaseTest {

    private val mockedMovieList = listOf(Movie(1, "Harry Poster"))
    private lateinit var movieListUseCase: GetMovieListUseCase

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        Mockito.`when`(moviesRepository.movies()).thenReturn(Either.Right(mockedMovieList))
        movieListUseCase = GetMovieListUseCase(moviesRepository)
    }

    @Test
    fun getMovies_ReturnsTrue() {
        runBlocking {
            assertEquals(movieListUseCase.run(UseCase.None()), Either.Right(mockedMovieList))
        }
    }
}