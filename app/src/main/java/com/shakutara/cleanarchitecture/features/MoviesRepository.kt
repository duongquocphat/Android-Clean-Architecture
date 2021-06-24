package com.shakutara.cleanarchitecture.features

import com.shakutara.cleanarchitecture.core.exception.Failure
import com.shakutara.cleanarchitecture.core.functional.Either
import com.shakutara.cleanarchitecture.core.platform.NetworkHandler
import com.shakutara.cleanarchitecture.features.list.Movie
import com.shakutara.cleanarchitecture.features.detail.MovieDetails
import com.shakutara.cleanarchitecture.features.detail.MovieDetailsEntity
import retrofit2.Call
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetail(id: Int): Either<Failure, MovieDetails>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: MoviesService
    ) : MoviesRepository {
        override fun movieDetail(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isConnected) {
                true ->
                    request(
                        service.movieDetails(movieId),
                        {
                            it.toMovieDetails()
                        },
                        MovieDetailsEntity.empty()
                    )
                false, null ->
                    Either.Left(Failure.NetworkError)
            }
        }

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                true ->
                    request(
                        service.movies(),
                        {
                            it.map { movieEntity ->
                                movieEntity.toMovie()
                            }
                        },
                        emptyList()
                    )
                false, null ->
                    Either.Left(Failure.NetworkError)
            }
        }

        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true ->
                        Either.Right(transform((response.body() ?: default)))
                    false ->
                        Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}
