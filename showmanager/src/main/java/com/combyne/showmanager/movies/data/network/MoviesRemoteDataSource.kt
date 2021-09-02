package com.combyne.showmanager.movies.data.network

import com.combyne.showmanager.MoviesQuery
import my.com.m1.repository.ResultModel


interface MoviesRemoteDataSource {
    suspend fun getMovies(first: Int, skip: Int): ResultModel<List<MoviesQuery.Movie>?>
}