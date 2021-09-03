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
) : PagingSource<Int, MoviesQuery.Movie>() {

    companion object {
        const val PAGE_SIZE = 20
        const val STARTING_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesQuery.Movie> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apolloClient.createApollo()
                .query(
                    MoviesQuery(
                        Input.fromNullable(PAGE_SIZE),
                        Input.fromNullable(pageIndex)
                    )
                )
                .await().data?.response()

            val movies = response?.movies()

            val nextKey =
                if (response?.pageInfo()?.hasNextPage() == false) {
                    null
                } else {
                    // By default, initial load size = 3 * PAGE_SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                  pageIndex + PAGE_SIZE
                }
            LoadResult.Page(
                data = movies!!.toMutableList(),
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
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
    override fun getRefreshKey(state: PagingState<Int, MoviesQuery.Movie>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
