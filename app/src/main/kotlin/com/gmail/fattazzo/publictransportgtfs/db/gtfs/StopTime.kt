package com.gmail.fattazzo.publictransportgtfs.db.gtfs

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import com.gmail.fattazzo.gtfs.domain.type.StopDropOffType
import com.gmail.fattazzo.gtfs.domain.type.StopPickupType
import com.gmail.fattazzo.gtfs.domain.type.TimepointType

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "stop_times", id = "_id")
class StopTime : BaseModel() {

    @Column(index = true, notNull = true)
    lateinit var tripId: String

    @Column(notNull = true)
    lateinit var arrivalTime: String

    @Column(notNull = true)
    lateinit var departureTime: String

    @Column(index = true, notNull = true)
    lateinit var stopId: String

    @Column(notNull = true)
    var sequence: Int = 0

    @Column
    var headsign: String? = null

    @Column
    var pickupType: StopPickupType? = StopPickupType.REGULARLY

    @Column
    var dropOffType: StopDropOffType? = StopDropOffType.REGULARLY

    @Column(length = 10)
    var shapeDistTraveled: String? = null

    @Column
    var timepoint: TimepointType? = TimepointType.EXACT

    val trip: Trip by lazy {
        Select().from(Trip::class.java)
                .where("agencyRef = ?", agencyRef)
                .and("id = ?", tripId)
                .executeSingle() as Trip
    }

    val stop: Stop by lazy {
        Select().from(Stop::class.java)
                .where("agencyRef = ?", agencyRef)
                .and("id = ?", stopId)
                .executeSingle() as Stop
    }
}