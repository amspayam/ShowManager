package com.combyne.showmanager.movies.domain

import com.combyne.core.usecase.AsyncSuspendUseCase
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.domain.model.MovieRequestModel
import my.com.m1.repository.ResultModel

class MoviesUseCase(
    private val repository: MoviesRepository,
) : AsyncSuspendUseCase<MovieRequestModel, ResultModel<List<MoviesQuery.Movie>?>> {

    override suspend fun executeAsync(rq: MovieRequestModel): ResultModel<List<MoviesQuery.Movie>?> {
        return repository.getMovies(first = rq.first, rq.skip)
    }
}