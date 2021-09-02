package com.combyne.showmanager.addmovie.domain

import com.combyne.core.usecase.AsyncSuspendUseCase
import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel
import my.com.m1.repository.ResultModel

class AddMovieUseCase(
    private val repository: AddMovieRepository,
) : AsyncSuspendUseCase<AddMovieRequestModel, ResultModel<CreateMovieMutation.Movie?>> {

    override suspend fun executeAsync(rq: AddMovieRequestModel):ResultModel<CreateMovieMutation.Movie?> {
        return repository.addNewShow(rq)
    }
}