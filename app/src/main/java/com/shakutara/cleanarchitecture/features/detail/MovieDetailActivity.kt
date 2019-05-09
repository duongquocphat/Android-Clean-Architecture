package com.shakutara.cleanarchitecture.features.detail

import android.os.Bundle
import android.widget.Toast
import com.shakutara.cleanarchitecture.R
import com.shakutara.cleanarchitecture.core.exception.Failure
import com.shakutara.cleanarchitecture.core.extension.failure
import com.shakutara.cleanarchitecture.core.extension.observe
import com.shakutara.cleanarchitecture.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        appComponent.inject(this)

        movieDetailViewModel = viewModel(viewModelFactory) {
            observe(movieDetail, ::showMovieDetail)
            failure(failure, ::handleFailure)
        }

        movieDetailViewModel.loadMovieDetail()
    }

    private fun showMovieDetail(movieDetails: MovieDetails?) {
        tvDetail.text = movieDetails?.title
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkError -> renderError("Network Error")
            is Failure.ServerError -> renderError("Server Error")
        }
    }

    private fun renderError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
