package com.shakutara.cleanarchitecture.features.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shakutara.cleanarchitecture.core.interactor.UseCase
import com.shakutara.cleanarchitecture.core.platform.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val getMovieListUseCase: GetMovieListUseCase) : BaseViewModel() {

    var movies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun loadMovies() = viewModelScope.launch {
        getMovieListUseCase(UseCase.None()) {
            it.either(
                ::handleFailure,
                ::handleMovieList
            )
        }
    }

    private fun handleMovieList(movies: List<Movie>) {
        this.movies.postValue(movies)
    }
}