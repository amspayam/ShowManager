package com.combyne.showmanager.addmovie.presenter

import androidx.lifecycle.MutableLiveData
import com.combyne.core.view.ViewState
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.R
import com.combyne.showmanager.addmovie.domain.AddMovieUseCase
import com.combyne.showmanager.addmovie.domain.model.AddMovieRequestModel
import com.combyne.uikit.base.viewmodel.BaseViewModel
import com.combyne.uikit.base.viewmodel.MessageMaster
import com.combyne.uikit.base.viewmodel.MessageTypeEnum

class AddMovieViewModel(
    var addShowUseCase: AddMovieUseCase
) : BaseViewModel() {

    val addShowViewStateLiveData = MutableLiveData<ViewState<List<MoviesQuery.Movie>?>>()

    fun addNewShow(title: String?, releaseDate: String?, seasons: String?) {
        val isValid = checkAddShowDataValidity(
            title = title,
            releaseDate = releaseDate,
            seasons = seasons
        )
        if (!isValid)
            return

        addShowViewStateLiveData.value = ViewState.ViewLoading
        track {
            addShowUseCase.executeAsync(
                AddMovieRequestModel(
                    title = title!!,
                    releaseDate = releaseDate!!,
                    seasons = seasons!!.toDouble()
                )
            )
//                .executeUseCase({
//                    addShowViewStateLiveData.value = ViewState.ViewData(it)
//                }, {
//                    addShowViewStateLiveData.value = ViewState.ViewError(it.message, it.status)
//                })
        }
    }

    private fun checkAddShowDataValidity(
        title: String?,
        releaseDate: String?,
        seasons: String?
    ): Boolean {
        var isValid = true
        if (title.isNullOrEmpty()) {
            message.value = MessageMaster(
                type = MessageTypeEnum.VIEW,
                messageResourceId = R.string.field_required, viewId = R.id.tvShowEditText
            )
            isValid = false
        }
        if (releaseDate.isNullOrEmpty()) {
            message.value = MessageMaster(
                type = MessageTypeEnum.VIEW,
                messageResourceId = R.string.field_required, viewId = R.id.releaseDateEditText
            )
            isValid = false
        }
        if (seasons.isNullOrEmpty()) {
            message.value = MessageMaster(
                type = MessageTypeEnum.VIEW,
                messageResourceId = R.string.field_required, viewId = R.id.seasonsEditText
            )
            isValid = false
        }
        return isValid
    }

}