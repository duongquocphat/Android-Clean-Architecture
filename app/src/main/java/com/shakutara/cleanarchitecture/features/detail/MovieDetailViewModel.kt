package com.shakutara.cleanarchitecture.features.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shakutara.cleanarchitecture.core.platform.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel
@Inject constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase) : BaseViewModel() {

    var movieDetail: MutableLiveData<MovieDetails> = MutableLiveData()

    fun loadMovieDetail() {
        viewModelScope.launch {
            getMovieDetailUseCase(
                GetMovieDetailUseCase.Params(38001)
            ) {
                it.either(::handleFailure, ::handleMovieDetail)
            }
        }
    }

    private fun handleMovieDetail(movieDetails: MovieDetails) {
        this.movieDetail.postValue(movieDetails)
    }
}