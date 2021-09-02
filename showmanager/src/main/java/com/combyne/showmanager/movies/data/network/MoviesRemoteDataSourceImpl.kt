package com.combyne.showmanager.movies.data.network

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.combyne.showmanager.MoviesQuery
import my.com.m1.repository.ResultModel
import my.com.m1.repository.network.NetworkManager
import my.com.m1.repository.network.entity.RestErrorResponse

class MoviesRemoteDataSourceImpl(
    private val apolloClient: NetworkManager
) : MoviesRemoteDataSource {

    override suspend fun getMovies(first: Int, skip: Int): ResultModel<List<MoviesQuery.Movie>?> {
        try {
            return ResultModel.Success(
                apolloClient.createApollo()
                    .query(
                        MoviesQuery(
                            Input.fromNullable(first),
                            Input.fromNullable(skip)
                        )
                    )
                    .await().data?.response()?.movies()
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