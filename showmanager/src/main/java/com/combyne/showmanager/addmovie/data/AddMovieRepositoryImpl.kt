package com.combyne.showmanager.addmovie.data

import com.combyne.repository.ResultModel
import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.data.network.AddMovieRemoteDataSource
import com.combyne.showmanager.addmovie.domain.AddMovieRepository
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel

class AddMovieRepositoryImpl(private val dataSource: AddMovieRemoteDataSource) : AddMovieRepository {

    override suspend fun addNewShow(requestModel: AddMovieRequestModel): ResultModel<CreateMovieMutation.Movie?> {
        return dataSource.addNewShow(requestModel)
    }
}