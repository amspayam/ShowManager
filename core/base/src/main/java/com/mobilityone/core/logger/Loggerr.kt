package com.combyne.core.logger

import com.combyne.onegold.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * Created by p.miri on 11/1/2016.
 */

class Loggerr {

    init {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(0) // (Optional) How many method line to show. Default 2
            .methodOffset(7) // (Optional) Hides internal method calls up to offset. Default 5
            .tag("Test -> ") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    companion object {

        fun i(message: String?) {
            if (BuildConfig.DEBUG) {
                Logger.i(message!!)
            }
        }

        fun i(tag: String?, message: String?) {
            if (BuildConfig.DEBUG) {
                Logger.t(tag).i(message!!)
            }
        }

        fun e(message: String?) {
            if (BuildConfig.DEBUG) {
                Logger.e(message!!)
            }
        }

        fun e(tag: String?, message: String?) {
            if (BuildConfig.DEBUG) {
                Logger.t(tag).e(message!!)
            }
        }

        fun d(message: String?) {
            if (BuildConfig.DEBUG) {
                Logger.d(message)
            }
        }

        fun d(tag: String?, message: String?) {
            if (BuildConfig.DEBUG) {
                Logger.t(tag).d(message)
            }
        }

        fun v(message: String?) {
            if (BuildConfig.DEBUG) {
                Logger.v(message!!)
            }
        }

        fun v(tag: String?, message: String?) {
            if (BuildConfig.DEBUG) {
                Logger.t(tag).v(message!!)
            }
        }

        fun json(json: String?) {
            if (BuildConfig.DEBUG) {
                Logger.json(json)
            }
        }

        fun json(tag: String?, json: String?) {
            if (BuildConfig.DEBUG) {
                Logger.t(tag).json(json)
            }
        }
    }


}