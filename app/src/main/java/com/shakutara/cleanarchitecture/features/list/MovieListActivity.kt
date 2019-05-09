package com.shakutara.cleanarchitecture.features.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.shakutara.cleanarchitecture.R
import com.shakutara.cleanarchitecture.core.exception.Failure
import com.shakutara.cleanarchitecture.core.extension.failure
import com.shakutara.cleanarchitecture.core.extension.observe
import com.shakutara.cleanarchitecture.core.platform.BaseActivity
import com.shakutara.cleanarchitecture.features.detail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            tvMovie -> launchMovieDetailActivity()
        }
    }

    private fun launchMovieDetailActivity() {
        startActivity(Intent(this, MovieDetailActivity::class.java))
    }

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        appComponent.inject(this)

        moviesViewModel = viewModel(viewModelFactory) {
            observe(movies, ::getMoviesList)

            failure(failure, ::handleFailure)
        }

        moviesViewModel.loadMovies()

        // views click
        tvMovie.setOnClickListener(this)
    }

    private fun getMoviesList(movies: List<Movie>?) {
        tvMovie.text = movies.toString()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkError -> renderFailure("failure_network_connection")
            is Failure.ServerError -> renderFailure("failure_server_error")
        }
    }

    private fun renderFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
