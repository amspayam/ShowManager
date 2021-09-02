package com.combyne.showmanager.addmovie.data.network

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.combyne.showmanager.CreateMovieMutation
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel
import my.com.m1.repository.ResultModel
import my.com.m1.repository.network.NetworkManager
import my.com.m1.repository.network.entity.RestErrorResponse

class AddMovieRemoteDataSourceImpl(
    private val apolloClient: NetworkManager
) : AddMovieRemoteDataSource {

    override suspend fun addNewShow(request: AddMovieRequestModel): ResultModel<CreateMovieMutation.Movie?> {
        try {
            return ResultModel.Success(
                apolloClient.createApollo()
                    .mutate(
                        CreateMovieMutation(
                            request.title,
                            Input.fromNullable(request.releaseDate),
                            Input.fromNullable(request.seasons)
                        )
                    )
                    .await().data?.createMovie()?.movie()
            )
        } catch (exception: ApolloException) {
            return ResultModel.Error(
                RestErrorResponse(
                    status = 0,
                    error = exception.localizedMessage ?: "Error"
                )
            )
        }
    }

}