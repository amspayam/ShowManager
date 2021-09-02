package com.combyne.uikit.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalendarUtil {
    companion object {
        /**
         * @param  inputDate ex: "2021-04-03T12:30:00"
         * @param  toDateFormatter ex: "2021-04-03T12:30:00"
         * @return ex: Calendar class
         */
        fun stringDateToCalendar(inputDate: String, toDateFormatter: String): Calendar? {
            val inputDateFormat = SimpleDateFormat(toDateFormatter, Locale.getDefault())
            return try {
                inputDateFormat.parse(inputDate)?.let {
                    val cal = Calendar.getInstance()
                    cal.time = it
                    cal
                }
            } catch (e: ParseException) {
                null
            }

        }

        /**
         * @param  inputDate ex: "2021-04-03T12:30:00"
         * @param  toDateFormatter ex: "2021-04-03T12:30:00"
         * @return ex: Date class
         */
        fun stringDateToDate(inputDate: String, toDateFormatter: String): Date? {
            val inputDateFormat = SimpleDateFormat(toDateFormatter, Locale.getDefault())
            return try {
                inputDateFormat.parse(inputDate)
            } catch (e: ParseException) {
                null
            }

        }

        /**
         * @param  inputDate ex: "2021-04-03T12:30:00"
         * @param  toDateFormatter ex: "2021-04-03T12:30:00"
         * @return ex: Calendar class
         */
        fun calendarToStringDate(inputDate: Calendar?, toDateFormatter: String): String {
            val inputDateFormat = SimpleDateFormat(toDateFormatter, Locale.getDefault())
            return inputDate?.let {
                inputDateFormat.format(it.time)
            } ?: kotlin.run { "none" }
        }

        const val DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val DATE_FORMAT_MONTH_NAME_DAY_YEAR = "MMM dd, yyyy"
    }
}