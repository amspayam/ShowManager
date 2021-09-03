package com.combyne.showmanager.addmovie.domain

import com.combyne.core.usecase.AsyncSuspendUseCase
import com.combyne.repository.ResultModel
import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel

class AddMovieUseCase(
    private val repository: AddMovieRepository,
) : AsyncSuspendUseCase<AddMovieRequestModel, ResultModel<CreateMovieMutation.Movie?>> {

    override suspend fun executeAsync(rq: AddMovieRequestModel):ResultModel<CreateMovieMutation.Movie?> {
        return repository.addNewShow(rq)
    }
}