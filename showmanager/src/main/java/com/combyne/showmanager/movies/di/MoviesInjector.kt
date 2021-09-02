package com.combyne.showmanager.movies.di

import com.combyne.showmanager.movies.di.modules.moviesDataModule
import com.combyne.showmanager.movies.di.modules.moviesPresentationModule
import com.combyne.showmanager.movies.di.modules.moviesUseCaseModule
import org.koin.core.module.Module

object MoviesInjector {
    fun provideDependencies(): List<Module> {
        return listOf(
            moviesPresentationModule,
            moviesUseCaseModule,
            moviesDataModule
        )
    }
}