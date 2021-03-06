package com.combyne.showmanager.addmovie.di.modules

import com.combyne.showmanager.addmovie.presenter.AddMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addMoviePresentationModule = module {
    viewModel { AddMovieViewModel(get()) }
}