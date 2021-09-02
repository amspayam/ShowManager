package com.combyne.showmanager.addmovie.domain

import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel
import my.com.m1.repository.ResultModel

interface AddMovieRepository {
    suspend fun addNewShow(requestModel: AddMovieRequestModel): ResultModel<CreateMovieMutation.Movie?>
}