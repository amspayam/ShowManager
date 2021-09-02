package com.combyne.uikit.extension.view

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun View.getColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this.context, id)
}

fun View.getHexStringColor(@ColorRes id: Int): String {
    return "#${Integer.toHexString(ContextCompat.getColor(this.context, id))}"
}