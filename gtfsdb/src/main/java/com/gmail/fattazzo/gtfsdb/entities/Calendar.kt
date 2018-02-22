package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.gmail.fattazzo.gtfsdb.entities.types.DayServiceType
import java.util.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "calendar", id = "_id")
class Calendar : BaseModel() {

    @Column(name = "service_id", notNull = true, unique = true)
    lateinit var serviceId: String

    @Column(notNull = true)
    var monday: DayServiceType = DayServiceType.NOT_AVAILABLE

    @Column(notNull = true)
    var tuesday: DayServiceType = DayServiceType.NOT_AVAILABLE

    @Column(notNull = true)
    var wednesday: DayServiceType = DayServiceType.NOT_AVAILABLE

    @Column(notNull = true)
    var thursday: DayServiceType = DayServiceType.NOT_AVAILABLE

    @Column(notNull = true)
    var friday: DayServiceType = DayServiceType.NOT_AVAILABLE

    @Column(notNull = true)
    var saturday: DayServiceType = DayServiceType.NOT_AVAILABLE

    @Column(notNull = true)
    var sunday: DayServiceType = DayServiceType.NOT_AVAILABLE

    @Column(name = "start_date", notNull = true)
    lateinit var startDate: Date

    @Column(name = "end_date", notNull = true)
    lateinit var endDate: Date
}