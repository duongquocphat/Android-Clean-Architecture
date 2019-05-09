package com.shakutara.cleanarchitecture.features.detail

import android.arch.lifecycle.MutableLiveData
import com.shakutara.cleanarchitecture.core.platform.BaseViewModel
import javax.inject.Inject

class MovieDetailViewModel
@Inject constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase) : BaseViewModel() {

    var movieDetail: MutableLiveData<MovieDetails> = MutableLiveData()

    fun loadMovieDetail() =
        getMovieDetailUseCase(GetMovieDetailUseCase.Params(38001)) { it.either(::handleFailure, ::handleMovieDetail) }

    private fun handleMovieDetail(movieDetails: MovieDetails) {
        this.movieDetail.value = movieDetails
    }
}