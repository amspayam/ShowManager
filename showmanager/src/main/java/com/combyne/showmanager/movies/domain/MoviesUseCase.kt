package com.combyne.showmanager.movies.domain

import com.combyne.core.usecase.AsyncSuspendUseCase
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.domain.model.MovieRequestModel

class MoviesUseCase(
    private val repository: MoviesRepository,
) : AsyncSuspendUseCase<MovieRequestModel, List<MoviesQuery.Movie>?> {

    override suspend fun executeAsync(rq: MovieRequestModel): List<MoviesQuery.Movie>? {
        return repository.getMovies(first = rq.first, rq.skip)?.response()?.movies()
    }
}