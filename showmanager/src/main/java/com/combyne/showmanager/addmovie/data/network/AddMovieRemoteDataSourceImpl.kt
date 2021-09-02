package com.combyne.showmanager.addmovie.data.network

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel
import my.com.m1.repository.network.NetworkManager

class AddMovieRemoteDataSourceImpl(
    private val apolloClient: NetworkManager
) : AddMovieRemoteDataSource {

    override suspend fun addNewShow(request: AddMovieRequestModel): CreateMovieMutation.Data? {
        return apolloClient.createApollo()
            .mutate(
                CreateMovieMutation(
                    request.title,
                    Input.fromNullable(request.releaseDate),
                    Input.fromNullable(request.seasons)
                )
            )
            .await().data
    }

}