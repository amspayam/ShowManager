package com.combyne.showmanager.movies.data

import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.data.network.MoviesRemoteDataSource
import com.combyne.showmanager.movies.domain.MoviesRepository

class MoviesRepositoryImpl(private val dataSource: MoviesRemoteDataSource) :
    MoviesRepository {

    override suspend fun getMovies(first: Int, skip: Int): MoviesQuery.Data? {
        return dataSource.getMovies(first, skip)
    }
}