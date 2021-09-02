package com.combyne.showmanager.addmovie.di

import com.combyne.showmanager.addmovie.di.modules.addShowDataModule
import com.combyne.showmanager.addmovie.di.modules.addShowPresentationModule
import com.combyne.showmanager.addmovie.di.modules.addShowUseCaseModule
import org.koin.core.module.Module

object AddMovieInjector {
    fun provideDependencies(): List<Module> {
        return listOf(
            addShowPresentationModule,
            addShowUseCaseModule,
            addShowDataModule
        )
    }
}