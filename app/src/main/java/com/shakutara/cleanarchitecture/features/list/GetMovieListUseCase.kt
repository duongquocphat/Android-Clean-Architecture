package com.shakutara.cleanarchitecture.features.list

import com.shakutara.cleanarchitecture.core.interactor.UseCase
import com.shakutara.cleanarchitecture.features.MoviesRepository
import javax.inject.Inject

class GetMovieListUseCase
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, UseCase.None>() {
    override suspend fun run(params: None) = moviesRepository.movies()
}
