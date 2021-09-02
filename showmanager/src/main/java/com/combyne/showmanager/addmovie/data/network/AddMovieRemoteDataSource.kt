package com.combyne.showmanager.addmovie.data.network

import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel
import my.com.m1.repository.ResultModel

interface AddMovieRemoteDataSource {
    suspend fun addNewShow(request: AddMovieRequestModel): ResultModel<CreateMovieMutation.Movie?>
}