package com.combyne.showmanager.movies.data

import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.data.network.MoviesRemoteDataSource
import com.combyne.showmanager.movies.domain.MoviesRepository
import my.com.m1.repository.ResultModel

class MoviesRepositoryImpl(private val dataSource: MoviesRemoteDataSource) :
    MoviesRepository {

    override suspend fun getMovies(first: Int, skip: Int): ResultModel<List<MoviesQuery.Movie>?> {
        return dataSource.getMovies(first, skip)
    }
}