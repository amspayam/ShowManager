package com.combyne.showmanager.movies.presenter.adapter

import android.view.ViewGroup
import com.combyne.showmanager.R
import com.combyne.showmanager.databinding.ItemMovieBinding
import com.combyne.showmanager.movies.presenter.adapter.viewholder.movie.MovieViewHolder
import com.combyne.uikit.base.adapter.BaseRecyclerAdapter
import com.combyne.uikit.base.adapter.BaseViewHolder
import com.combyne.uikit.extension.permitive.inflate

class MovieAdapter : BaseRecyclerAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        return MovieViewHolder(
            binding = ItemMovieBinding.bind(parent.inflate(viewType))
        )
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_movie
    }
}