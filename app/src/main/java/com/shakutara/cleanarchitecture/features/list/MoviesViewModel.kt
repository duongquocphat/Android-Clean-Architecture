package com.shakutara.cleanarchitecture.features.list

import android.arch.lifecycle.MutableLiveData
import com.shakutara.cleanarchitecture.core.interactor.UseCase
import com.shakutara.cleanarchitecture.core.platform.BaseViewModel
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val getMovieListUseCase: GetMovieListUseCase) : BaseViewModel() {

    var movies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun loadMovies() = getMovieListUseCase(UseCase.None()) { it.either(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(movies: List<Movie>) {
        this.movies.value = movies
    }
}