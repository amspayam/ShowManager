package com.combyne.showmanager.movies.presenter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.combyne.showmanager.MoviesQuery

class MovieDiffCallBack : DiffUtil.ItemCallback<MoviesQuery.Movie>() {
    override fun areItemsTheSame(oldItem: MoviesQuery.Movie, newItem: MoviesQuery.Movie): Boolean {
        return oldItem.movie()?.id() == newItem.movie()?.id()
    }

    override fun areContentsTheSame(oldItem: MoviesQuery.Movie, newItem: MoviesQuery.Movie): Boolean {
        return oldItem == newItem
    }
}