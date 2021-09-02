package com.combyne.main.mainactivity.di

import com.combyne.main.mainactivity.di.modules.MainActivityPresentationModule


object MainActivityInjector {
    fun provideDependencies() = listOf(
        MainActivityPresentationModule
    )
}