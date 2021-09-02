package com.combyne.showmanager.shows.di

import com.combyne.showmanager.shows.di.modules.showsPresentationModule
import org.koin.core.module.Module

object ShowsInjector {
    fun provideDependencies(): List<Module> {
        return listOf(
            showsPresentationModule
        )
    }
}