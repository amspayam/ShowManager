package com.combyne.showmanager.addmovie.di.modules

import com.combyne.showmanager.addmovie.domain.AddMovieUseCase
import org.koin.dsl.module

val addMovieUseCaseModule = module {
    factory { AddMovieUseCase(get()) }
}