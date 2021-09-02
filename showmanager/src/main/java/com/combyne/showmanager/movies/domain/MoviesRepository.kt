package com.combyne.showmanager.movies.domain

import com.combyne.showmanager.MoviesQuery
import my.com.m1.repository.ResultModel

interface MoviesRepository {
    suspend fun getMovies(first: Int, skip: Int): ResultModel<List<MoviesQuery.Movie>?>
}