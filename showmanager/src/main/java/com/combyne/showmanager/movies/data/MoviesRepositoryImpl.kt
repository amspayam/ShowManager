package com.combyne.showmanager.movies.data

import androidx.paging.PagingData
import com.combyne.repository.ResultModel
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.data.network.MoviesRemoteDataSource
import com.combyne.showmanager.movies.domain.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(private val dataSource: MoviesRemoteDataSource) :
    MoviesRepository {

    override suspend fun getMovies(): ResultModel<Flow<PagingData<MoviesQuery.Movie>>> {
        return dataSource.getMovies()
    }
}