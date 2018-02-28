package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class CalendarFile(
        override val name: String = "calendar.txt",
        override val columnsHeader: Array<String> = arrayOf("service_id", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "start_date", "end_date"),
        override val columnsType: Array<DataType> = arrayOf(STRING, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, DATE, DATE))
    : FileDefinition