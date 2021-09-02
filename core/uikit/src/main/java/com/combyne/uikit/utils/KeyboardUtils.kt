package com.combyne.uikit.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import java.util.*

/**
 * Created by p.kokabi on 25/04/2021
 */

object KeyboardUtils {

    @JvmStatic
    fun hideKeyboard(activity: Activity?) {
        try {
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(activity.currentFocus)?.windowToken, 0)
        } catch (ignored: Exception) {
        }
    }

    @JvmStatic
    fun showKeyboard(activity: Activity?) {
        try {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        } catch (ignored: Exception) {
        }
    }
}