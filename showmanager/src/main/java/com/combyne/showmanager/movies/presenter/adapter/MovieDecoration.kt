package com.combyne.showmanager.movies.presenter.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.combyne.uikit.extension.permitive.dpToPx

class MovieDecoration(context: Context) : ItemDecoration() {

    private val padding0dp = 0.dpToPx(context)
    private val padding24dp = 24.dpToPx(context)
    private val padding32dp = 32.dpToPx(context)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        outRect.right = padding32dp
        outRect.left = padding32dp
        outRect.top = if (position == 0) padding32dp else padding24dp
        outRect.bottom = if (position == state.itemCount - 1) padding32dp else padding0dp
    }

}