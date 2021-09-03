package com.combyne.repository.di


import com.combyne.repository.network.NetworkManager
import org.koin.dsl.module

object RepositoryInjector {

    fun provideDependencies() = module {

        single { NetworkManager(get(), get()) }

    }
}