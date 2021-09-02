package com.combyne.uikit.extension.context

import android.content.Context


/**
 * Created by p.kokabi on 25/05/2021
 */


fun Context.screenWidth() = this.resources.displayMetrics.widthPixels

fun Context.screenHeight() = this.resources.displayMetrics.heightPixels


