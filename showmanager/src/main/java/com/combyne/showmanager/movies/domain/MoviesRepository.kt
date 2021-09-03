package com.combyne.showmanager.movies.domain

import androidx.paging.PagingData
import com.combyne.repository.ResultModel
import com.combyne.showmanager.MoviesQuery
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): ResultModel<Flow<PagingData<MoviesQuery.Movie>>>
}