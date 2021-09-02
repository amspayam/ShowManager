package com.combyne.showmanager.movies.di.modules

import com.combyne.showmanager.movies.data.MoviesRepositoryImpl
import com.combyne.showmanager.movies.data.network.MoviesRemoteDataSource
import com.combyne.showmanager.movies.data.network.MoviesRemoteDataSourceImpl
import com.combyne.showmanager.movies.domain.MoviesRepository
import org.koin.dsl.module

val moviesDataModule = module {

    factory<MoviesRepository> { MoviesRepositoryImpl(get()) }
    factory<MoviesRemoteDataSource> { MoviesRemoteDataSourceImpl(get()) }

}