package com.combyne.showmanager.addmovie.domain

import com.combyne.repository.ResultModel
import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel

interface AddMovieRepository {
    suspend fun addNewShow(requestModel: AddMovieRequestModel): ResultModel<CreateMovieMutation.Movie?>
}