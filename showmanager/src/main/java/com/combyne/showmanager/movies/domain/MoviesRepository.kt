package com.combyne.showmanager.movies.domain

import com.combyne.showmanager.MoviesQuery


interface MoviesRepository {
    suspend fun getMovies(first: Int, skip: Int): MoviesQuery.Data?
}