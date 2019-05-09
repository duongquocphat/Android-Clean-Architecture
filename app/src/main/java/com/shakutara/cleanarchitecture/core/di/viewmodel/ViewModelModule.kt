package com.shakutara.cleanarchitecture.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.shakutara.cleanarchitecture.features.detail.MovieDetailViewModel
import com.shakutara.cleanarchitecture.features.list.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindsMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindsMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel
}