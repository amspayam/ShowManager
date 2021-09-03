package com.combyne.showmanager.movies.presenter

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.domain.MoviesUseCase
import com.combyne.showmanager.movies.domain.model.MovieRequestModel
import com.combyne.uikit.base.viewmodel.BaseViewModel
import com.mobilityone.core.view.ViewState
import kotlinx.coroutines.flow.Flow
import my.com.m1.repository.executeUseCase

class MoviesViewModel(
    var addShowUseCase: MoviesUseCase
) : BaseViewModel() {

    val moviesViewStateLiveData = MutableLiveData<ViewState<Flow<PagingData<MoviesQuery.Movie>>>>()

    init {
        getMovies()
    }

    fun getMovies() {
        moviesViewStateLiveData.value = ViewState.ViewLoading
        track {
            addShowUseCase.executeAsync(Unit)
                .executeUseCase({ movies ->
                    moviesViewStateLiveData.value = ViewState.ViewData(movies)
                }, {
                    moviesViewStateLiveData.value = ViewState.ViewError(it.error, it.status)
                })
        }
    }

}