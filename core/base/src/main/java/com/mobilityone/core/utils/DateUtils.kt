package com.combyne.core.utils

import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by p.kokabi on 03/04/2021
 */
class DateUtils {

    companion object {

        /**
         * @return ex: 2021-04-03T16:01:32.563+0430
         */
        fun getCurrentDateAndTime(): String? {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH).format(Date())
        }

        /**
         * @param  date ex: "2021-04-03T12:30:00"
         * @return      ex: Date class
         */
        fun getUTCDateFormat(date: String): Date? {

            var dateLocal = date
            return if (TextUtils.isEmpty(dateLocal)) {
                Date()
            } else {
                val inputDateFormatString = "yyyy-MM-dd'T'HH:mm"
                if (dateLocal.contains("/")) {
                    dateLocal = dateLocal.replace("/", "-")
                }
                if (!dateLocal.contains("T")) {
                    dateLocal += "T00:00"
                }
                val inputDateFormat = SimpleDateFormat(inputDateFormatString, Locale.ENGLISH)
                try {
                    inputDateFormat.parse(dateLocal)
                } catch (e: ParseException) {
                    Date()
                }
            }
        }
    }

}