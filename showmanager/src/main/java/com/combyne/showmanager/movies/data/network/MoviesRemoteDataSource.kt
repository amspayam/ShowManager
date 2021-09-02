package com.combyne.showmanager.movies.data.network

import com.combyne.showmanager.MoviesQuery


interface MoviesRemoteDataSource {
    suspend fun getMovies(first: Int, skip: Int): MoviesQuery.Data?
}