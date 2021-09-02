package com.combyne.showmanager.addmovie.data.network

import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel


interface AddMovieRemoteDataSource {
    suspend fun addNewShow(request: AddMovieRequestModel): CreateMovieMutation.Data?
}