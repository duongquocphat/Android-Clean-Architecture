package com.shakutara.cleanarchitecture.features.detail

import com.shakutara.cleanarchitecture.core.interactor.UseCase
import com.shakutara.cleanarchitecture.features.MoviesRepository
import javax.inject.Inject

class GetMovieDetailUseCase
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<MovieDetails, GetMovieDetailUseCase.Params>() {
    override suspend fun run(params: Params) = moviesRepository.movieDetail(params.id)

    data class Params(val id: Int)
}
