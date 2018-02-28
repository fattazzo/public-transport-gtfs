package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class CalendarDatesFile(
        override val name: String = "calendar_dates.txt",
        override val columnsHeader: Array<String> = arrayOf("service_id", "date", "exception_type"),
        override val columnsType: Array<DataType> = arrayOf(STRING, DATE, NUMBER))
    : FileDefinition