package com.combyne.showmanager.movies.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.exception.ApolloException
import com.combyne.repository.ResultModel
import com.combyne.repository.network.NetworkManager
import com.combyne.repository.network.entity.RestErrorResponse
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.data.network.MoviesPagingSource.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

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