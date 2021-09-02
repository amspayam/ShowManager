package com.combyne.showmanager.addmovie.domain.model

data class AddMovieRequestModel(
    var title: String,
    val releaseDate: String,
    val seasons: Double
)