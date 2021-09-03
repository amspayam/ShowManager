package com.combyne.showmanager.movies.presenter

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.combyne.core.view.ViewState
import com.combyne.repository.executeUseCase
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.domain.MoviesUseCase
import com.combyne.uikit.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow

class MoviesViewModel(
    private var addShowUseCase: MoviesUseCase
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