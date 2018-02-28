package com.gmail.fattazzo.gtfsparser.files.processor.datatype

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 28/02/18
 */
object DataTypeProcessor {

    private val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)

    fun process(value: String?, type: DataType): String {
        if (value == null) {
            return "null"
        }
        return when (type) {
            DataType.STRING -> convertToString(value)
            DataType.NUMBER -> convertToNumber(value)
            DataType.BOOLEAN -> convertToBoolean(value)
            DataType.DATE -> convertToDate(value)
        }
    }

    private fun convertToString(value: String): String {
        return "'$value'"
    }

    private fun convertToDate(value: String): String {
        return try {
            val date = dateFormat.parse(value)
            date.time.toString()
        } catch (e: Exception) {
            value
        }
    }

    private fun convertToNumber(value: String): String {
        return if (value.isEmpty()) "null" else value
    }

    private fun convertToBoolean(value: String): String {
        return if (value.isEmpty()) "0" else value
    }
}