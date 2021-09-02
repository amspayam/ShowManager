package com.combyne.core.logger

import com.combyne.onegold.BuildConfig
import com.orhanobut.logger.Logger

/**
 * Created by p.kokabi on 14/06/2021
 */

fun Any.i(message: String?) {
    if (BuildConfig.DEBUG) {
        Logger.i(message ?: "Null variable!!!")
    }
}

fun Any.i(tag: String?, message: String?) {
    if (BuildConfig.DEBUG) {
        Logger.t(tag).i(message ?: "Null variable!!!")
    }
}

fun Any.e(message: String?) {
    if (BuildConfig.DEBUG) {
        Logger.e(message ?: "Null variable!!!")
    }
}

fun Any.e(tag: String?, message: String?) {
    if (BuildConfig.DEBUG) {
        Logger.t(tag).e(message ?: "Null variable!!!")
    }
}

fun Any.d(message: String?) {
    if (BuildConfig.DEBUG) {
        Logger.d(message)
    }
}

fun Any.d(tag: String?, message: String?) {
    if (BuildConfig.DEBUG) {
        Logger.t(tag).d(message)
    }
}

fun Any.v(message: String?) {
    if (BuildConfig.DEBUG) {
        Logger.v(message ?: "Null variable!!!")
    }
}

fun Any.v(tag: String?, message: String?) {
    if (BuildConfig.DEBUG) {
        Logger.t(tag).v(message ?: "Null variable!!!")
    }
}

fun Any.json(json: String?) {
    if (BuildConfig.DEBUG) {
        Logger.json(json)
    }
}

fun Any.json(tag: String?, json: String?) {
    if (BuildConfig.DEBUG) {
        Logger.t(tag).json(json)
    }
}