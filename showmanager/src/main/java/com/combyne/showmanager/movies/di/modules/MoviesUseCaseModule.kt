package com.combyne.showmanager.movies.di.modules

import com.combyne.showmanager.movies.domain.MoviesUseCase
import org.koin.dsl.module

val moviesUseCaseModule = module {
    factory { MoviesUseCase(get()) }
}