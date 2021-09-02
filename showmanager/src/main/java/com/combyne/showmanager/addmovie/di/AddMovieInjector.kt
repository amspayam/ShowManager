package com.combyne.showmanager.addmovie.di

import com.combyne.showmanager.addmovie.di.modules.addMovieDataModule
import com.combyne.showmanager.addmovie.di.modules.addMoviePresentationModule
import com.combyne.showmanager.addmovie.di.modules.addMovieUseCaseModule
import org.koin.core.module.Module

object AddMovieInjector {
    fun provideDependencies(): List<Module> {
        return listOf(
            addMoviePresentationModule,
            addMovieUseCaseModule,
            addMovieDataModule
        )
    }
}