package com.combyne.uikit.extension.permitive

import android.content.Context
import android.util.TypedValue

/**
 * Created by p.kokabi on 7/21/18.
 */

fun Int.pxToDp(context: Context?): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, this.toFloat(), context?.resources?.displayMetrics).toInt()
}

fun Int.dpToPx(context: Context?): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context?.resources?.displayMetrics).toInt()
}

fun Int.spToPx(context: Context?): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), context?.resources?.displayMetrics).toInt()
}

fun Int.pxToSp(context: Context?): Int {
    val scaledDensity = context?.resources?.displayMetrics?.scaledDensity
    return scaledDensity?.let {
        (this / scaledDensity).toInt()
    } ?: 0
}

