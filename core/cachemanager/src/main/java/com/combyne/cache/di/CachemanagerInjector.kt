package com.combyne.cache.di

import com.combyne.cache.CacheManager
import org.koin.dsl.module

object CachemanagerInjector {

    fun provideDependencies() = module {

        single { CacheManager(get()) }

    }
}