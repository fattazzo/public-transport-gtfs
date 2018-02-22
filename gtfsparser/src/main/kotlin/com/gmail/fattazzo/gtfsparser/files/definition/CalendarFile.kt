package com.gmail.fattazzo.gtfsparser.files.definition

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class CalendarFile(
        override val name: String = "calendar.txt",
        override val columnsHeader: Array<String> = arrayOf("service_id", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "start_date", "end_date"))
    : FileDefinition