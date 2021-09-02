package com.combyne.uikit.utils

import android.content.Context
import android.graphics.Typeface
import androidx.collection.LruCache
import androidx.core.content.res.ResourcesCompat


/**
 * Created by p.kokabi on 04/03/2021
 */
object FontUtils {

    private val typeFaceCache = LruCache<Int, Typeface?>(24)

    @JvmStatic
    fun getTypeFace(context: Context?, font: Int): Typeface? {
        var typeFace = typeFaceCache[font]
        if (typeFace == null && context != null) {
            typeFace = ResourcesCompat.getFont(context, font)
            typeFaceCache.put(font, typeFace!!)
        }
        return typeFace
    }
}