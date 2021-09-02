package com.combyne.cachemanager.cache.di

import com.combyne.cachemanager.cache.CacheManager
import org.koin.dsl.module

object CachemanagerInjector {

    fun provideDependencies() = module {

        single { CacheManager(get()) }

    }
}