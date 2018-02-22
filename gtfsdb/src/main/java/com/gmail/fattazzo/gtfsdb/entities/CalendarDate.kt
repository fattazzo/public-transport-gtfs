package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.gmail.fattazzo.gtfsdb.entities.types.DateExceptionType
import java.util.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "calendar_dates", id = "_id")
class CalendarDate : Model() {

    @Column(length = 50, notNull = true, index = true, uniqueGroups = ["calendar_dates_index"])
    lateinit var feedId: String

    @Column(name = "service_id", notNull = true, uniqueGroups = ["calendar_dates_index"])
    lateinit var serviceId: String

    @Column(notNull = true, uniqueGroups = ["calendar_dates_index"])
    lateinit var date: Date

    @Column(name = "exception_type", notNull = true)
    lateinit var exceptionType: DateExceptionType
}