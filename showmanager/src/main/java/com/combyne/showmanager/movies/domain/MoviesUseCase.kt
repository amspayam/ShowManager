package com.combyne.showmanager.movies.domain

import androidx.paging.PagingData
import com.combyne.core.usecase.AsyncSuspendUseCase
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.domain.model.MovieRequestModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import my.com.m1.repository.ResultModel
import my.com.m1.repository.map

class MoviesUseCase(
    private val repository: MoviesRepository,
) : AsyncSuspendUseCase<Unit, ResultModel<Flow<PagingData<MoviesQuery.Movie>>>> {

    override suspend fun executeAsync(rq: Unit): ResultModel<Flow<PagingData<MoviesQuery.Movie>>> {
        return repository.getMovies()
    }
}