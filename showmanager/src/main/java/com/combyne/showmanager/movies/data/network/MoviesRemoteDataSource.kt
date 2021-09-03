package com.combyne.showmanager.movies.data.network

import androidx.paging.PagingData
import com.combyne.showmanager.MoviesQuery
import kotlinx.coroutines.flow.Flow
import my.com.m1.repository.ResultModel


interface MoviesRemoteDataSource {
    suspend fun getMovies(): ResultModel<Flow<PagingData<MoviesQuery.Movie>>>
}