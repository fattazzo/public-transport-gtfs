package com.gmail.fattazzo.gtfsparser.files.definition

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class CalendarDatesFile(
        override val name: String = "calendar_dates.txt",
        override val columnsHeader: Array<String> = arrayOf("service_id", "date", "exception_type"))
    : FileDefinition