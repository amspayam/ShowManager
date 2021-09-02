package com.combyne.showmanager.addmovie.di.modules

import com.combyne.showmanager.addmovie.domain.AddMovieUseCase
import org.koin.dsl.module

val addShowUseCaseModule = module {
    factory { AddMovieUseCase(get()) }
}