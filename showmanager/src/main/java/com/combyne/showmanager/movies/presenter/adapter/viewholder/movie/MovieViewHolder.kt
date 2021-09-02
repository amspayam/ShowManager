package com.combyne.showmanager.movies.presenter.adapter.viewholder.movie

import android.annotation.SuppressLint
import com.combyne.showmanager.MoviesQuery
import com.combyne.showmanager.databinding.ItemMovieBinding
import com.combyne.uikit.base.adapter.BaseViewHolder
import com.combyne.uikit.utils.CalendarUtil
import com.combyne.uikit.utils.CalendarUtil.Companion.DATE_FORMAT_MONTH_NAME_DAY_YEAR
import com.combyne.uikit.utils.CalendarUtil.Companion.DATE_FORMAT_YEAR_MONTH_DAY
import com.combyne.uikit.utils.CalendarUtil.Companion.calendarToStringDate

class MovieViewHolder(
    val binding: ItemMovieBinding
) :
    BaseViewHolder<MoviesQuery.Movie>(binding.root) {

    @SuppressLint("SetTextI18n")
    override fun bind(data: MoviesQuery.Movie) {
        val date = CalendarUtil.stringDateToCalendar(
            data.movie()?.releaseDate().toString(),
            DATE_FORMAT_YEAR_MONTH_DAY
        ).let {
            calendarToStringDate(
                it,
                DATE_FORMAT_MONTH_NAME_DAY_YEAR
            )
        }
        binding.titleTextview.text = data.movie()?.title()
        binding.releaseDateTextview.text = date
        binding.seasonsTextview.text = ": ${data.movie()?.seasons()}"
    }

}