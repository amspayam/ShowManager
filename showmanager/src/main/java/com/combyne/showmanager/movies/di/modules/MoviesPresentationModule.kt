package com.combyne.showmanager.movies.di.modules

import com.combyne.showmanager.movies.presenter.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesPresentationModule = module {
    viewModel { MoviesViewModel(get()) }
}