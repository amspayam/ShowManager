package com.combyne.showmanager.movies.data

import androidx.paging.PagingData
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.data.network.MoviesRemoteDataSource
import com.combyne.showmanager.movies.domain.MoviesRepository
import kotlinx.coroutines.flow.Flow
import my.com.m1.repository.ResultModel

class MoviesRepositoryImpl(private val dataSource: MoviesRemoteDataSource) :
    MoviesRepository {

    override suspend fun getMovies(): ResultModel<Flow<PagingData<MoviesQuery.Movie>>> {
        return dataSource.getMovies()
    }
}