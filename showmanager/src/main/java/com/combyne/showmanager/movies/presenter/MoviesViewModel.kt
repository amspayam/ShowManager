package com.combyne.showmanager.movies.presenter

import androidx.lifecycle.MutableLiveData
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.domain.MoviesUseCase
import com.combyne.showmanager.movies.domain.model.MovieRequestModel
import com.combyne.uikit.base.viewmodel.BaseViewModel
import com.combyne.uikit.extension.mutablelivedata.notifyObserver
import com.mobilityone.core.view.ViewState
import my.com.m1.repository.executeUseCase

class MoviesViewModel(
    var addShowUseCase: MoviesUseCase
) : BaseViewModel() {

    val moviesViewStateLiveData = MutableLiveData<ViewState<List<MoviesQuery.Movie>?>>()
    val movieItemsLiveData = MutableLiveData<MutableList<Any>>(mutableListOf())

    init {
        getMovies(first = 20, skip = 0)
    }

    fun getMovies(first: Int, skip: Int) {
        moviesViewStateLiveData.value = ViewState.ViewLoading
        track {
            addShowUseCase.executeAsync(MovieRequestModel(first = first, skip = skip))
                .executeUseCase({ movies ->
                    movies?.let {
                        prepareItemsForAdapter(it)
                    }
                    moviesViewStateLiveData.value = ViewState.ViewData(movies)
                }, {
                    moviesViewStateLiveData.value = ViewState.ViewError(it.error, it.status)
                })
        }
    }

    private fun prepareItemsForAdapter(originalList: List<MoviesQuery.Movie>) {
        movieItemsLiveData.value?.clear()
        movieItemsLiveData.value?.addAll(originalList)
        movieItemsLiveData.notifyObserver()
    }

}