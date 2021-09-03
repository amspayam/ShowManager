package com.combyne.showmanager.movies.domain

import androidx.paging.PagingData
import com.combyne.showmanager.MoviesQuery
import kotlinx.coroutines.flow.Flow
import my.com.m1.repository.ResultModel

interface MoviesRepository {
    suspend fun getMovies(): ResultModel<Flow<PagingData<MoviesQuery.Movie>>>
}