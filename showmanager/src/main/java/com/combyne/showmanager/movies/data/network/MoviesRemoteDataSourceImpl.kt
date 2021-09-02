package com.combyne.showmanager.movies.data.network

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.combyne.showmanager.MoviesQuery
import my.com.m1.repository.network.NetworkManager

class MoviesRemoteDataSourceImpl(
    private val apolloClient: NetworkManager
) : MoviesRemoteDataSource {

    override suspend fun getMovies(first: Int, skip: Int): MoviesQuery.Data? {
        return apolloClient.createApollo()
            .query(
                MoviesQuery(
                    Input.fromNullable(first),
                    Input.fromNullable(skip)
                )
            )
            .await().data
    }

}