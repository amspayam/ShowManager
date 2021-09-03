package com.combyne.showmanager.movies.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.combyne.cachemanager.cache.CacheManager
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.data.network.MoviesPagingSource.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import my.com.m1.onegold.account.usecase.GetTokenUseCaseSync
import my.com.m1.repository.ResultModel
import my.com.m1.repository.network.NetworkManager
import my.com.m1.repository.network.entity.RestErrorResponse

class MoviesRemoteDataSourceImpl(
    private val apolloClient: NetworkManager,
) : MoviesRemoteDataSource {

    override suspend fun getMovies(): ResultModel<Flow<PagingData<MoviesQuery.Movie>>> {
        try {
            val pagingSource = MoviesPagingSource(apolloClient = apolloClient)
            val pager = Pager(
                config = PagingConfig(
                    pageSize = PAGE_SIZE,
                    enablePlaceholders = false
                )
            ) { pagingSource }.flow
            return ResultModel.Success(pager)
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