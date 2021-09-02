package com.combyne.uikit.extension.permitive.string

import android.graphics.Color
import androidx.annotation.ColorRes
import java.util.*

/**
 * Created by p.kokabi on 17/02/2021
 */

fun String.pascalCase() = split(" ").joinToString(" ") { it.capitalize(Locale.ROOT) }

fun String?.toColor(@ColorRes defaultColor: Int? = null): Int {
    if (!this.isNullOrBlank() && this.startsWith("#")) {
        return Color.parseColor(this)
    } else {
        defaultColor?.let {
            return defaultColor
        } ?: kotlin.run {
            return Color.parseColor("#000000")
        }
    }
}

fun String.add(vararg strings: String?): String {
    var result = this
    for (string in strings) {
        result += string
    }
    return result
}