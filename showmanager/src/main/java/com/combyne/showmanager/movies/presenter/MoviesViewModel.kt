package com.combyne.showmanager.movies.presenter

import androidx.lifecycle.MutableLiveData
import com.combyne.core.view.ViewState
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.movies.domain.MoviesUseCase
import com.combyne.showmanager.movies.domain.model.MovieRequestModel
import com.combyne.uikit.base.viewmodel.BaseViewModel

class MoviesViewModel(
    var addShowUseCase: MoviesUseCase
) : BaseViewModel() {

    val addShowViewStateLiveData = MutableLiveData<ViewState<List<MoviesQuery.Movie>?>>()

    init {
        getMovies(first = 20, skip = 0)
    }

    fun getMovies(first: Int, skip: Int) {
        addShowViewStateLiveData.value = ViewState.ViewLoading
        track {
            addShowUseCase.executeAsync(MovieRequestModel(first = first, skip = skip))
//                .executeUseCase({
//                    addShowViewStateLiveData.value = ViewState.ViewData(it)
//                }, {
//                    addShowViewStateLiveData.value = ViewState.ViewError(it.message, it.status)
//                })
        }
    }

}