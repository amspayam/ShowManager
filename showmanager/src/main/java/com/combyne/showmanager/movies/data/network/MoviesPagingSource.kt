package com.combyne.showmanager.movies.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.combyne.showmanager.MoviesQuery
import my.com.m1.repository.network.NetworkManager
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(
    private val apolloClient: NetworkManager
) : PagingSource<String, MoviesQuery.Movie>() {

    companion object {
        const val PAGE_SIZE = 20
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, MoviesQuery.Movie> {
        var endCursor: String? = params.key
        val startCursor: String?
        return try {
            val response = apolloClient.createApollo()
                .query(
                    MoviesQuery(
                        Input.fromNullable(endCursor),
                        Input.fromNullable(10)
                    )
                )
                .await().data?.response()

            val movies = response?.movies()

            startCursor = response?.pageInfo()?.startCursor()
            endCursor =
                if (response?.pageInfo()?.hasNextPage() == false) {
                    null
                } else {
                    response?.pageInfo()?.endCursor()
                }
            LoadResult.Page(
                data = movies!!.toMutableList(),
                prevKey = startCursor,
                nextKey = endCursor
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<String, MoviesQuery.Movie>): String? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}
