package com.gmail.fattazzo.publictransportgtfs.db.gtfs

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.gmail.fattazzo.gtfs.domain.type.DateExceptionType
import java.util.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "calendar_dates",id = "_id")
class CalendarDate : BaseModel() {

    @Column(notNull = true, unique = true)
    lateinit var serviceId: String

    @Column(notNull = true)
    lateinit var date: Date

    @Column(notNull = true)
    lateinit var exceptionType: DateExceptionType
}