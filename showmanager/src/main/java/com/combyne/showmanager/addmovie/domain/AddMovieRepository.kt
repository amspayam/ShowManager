package com.combyne.showmanager.addmovie.domain

import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel

interface AddMovieRepository {
    suspend fun addNewShow(requestModel: AddMovieRequestModel): CreateMovieMutation.Data?
}