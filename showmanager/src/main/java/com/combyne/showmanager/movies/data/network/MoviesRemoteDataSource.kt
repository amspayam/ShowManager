package com.combyne.showmanager.movies.data.network

import androidx.paging.PagingData
import com.combyne.repository.ResultModel
import com.combyne.showmanager.MoviesQuery
import kotlinx.coroutines.flow.Flow


interface MoviesRemoteDataSource {
    suspend fun getMovies(): ResultModel<Flow<PagingData<MoviesQuery.Movie>>>
}