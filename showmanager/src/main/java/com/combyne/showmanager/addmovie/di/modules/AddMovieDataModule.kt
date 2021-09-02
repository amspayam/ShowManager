package com.combyne.showmanager.addmovie.di.modules

import com.combyne.showmanager.addmovie.data.AddMovieRepositoryImpl
import com.combyne.showmanager.addmovie.data.network.AddMovieRemoteDataSource
import com.combyne.showmanager.addmovie.data.network.AddMovieRemoteDataSourceImpl
import com.combyne.showmanager.addmovie.domain.AddMovieRepository
import org.koin.dsl.module

val addShowDataModule = module {

    factory<AddMovieRepository> { AddMovieRepositoryImpl(get()) }
    factory<AddMovieRemoteDataSource> { AddMovieRemoteDataSourceImpl(get()) }

}