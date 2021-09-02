package com.combyne.showmanager.shows.di.modules

import com.combyne.showmanager.shows.presenter.ShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val showsPresentationModule = module {
    viewModel { ShowsViewModel() }
}