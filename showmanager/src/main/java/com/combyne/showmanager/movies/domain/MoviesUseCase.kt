package com.combyne.showmanager.movies.domain

import androidx.paging.PagingData
import com.combyne.core.usecase.AsyncSuspendUseCase
import com.combyne.repository.ResultModel
import com.combyne.showmanager.MoviesQuery
import kotlinx.coroutines.flow.Flow

class MoviesUseCase(
    private val repository: MoviesRepository,
) : AsyncSuspendUseCase<Unit, ResultModel<Flow<PagingData<MoviesQuery.Movie>>>> {

    override suspend fun executeAsync(rq: Unit): ResultModel<Flow<PagingData<MoviesQuery.Movie>>> {
        return repository.getMovies()
    }
}