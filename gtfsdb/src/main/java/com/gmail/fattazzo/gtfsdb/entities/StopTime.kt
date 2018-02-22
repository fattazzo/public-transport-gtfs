package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import com.gmail.fattazzo.gtfsdb.entities.types.StopDropOffType
import com.gmail.fattazzo.gtfsdb.entities.types.StopPickupType
import com.gmail.fattazzo.gtfsdb.entities.types.TimepointType

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "stop_times", id = "_id")
class StopTime : BaseModel() {

    @Column(name = "trip_id", index = true, notNull = true)
    lateinit var tripId: String

    @Column(name = "arrival_time", notNull = true)
    lateinit var arrivalTime: String

    @Column(name = "departure_time", notNull = true)
    lateinit var departureTime: String

    @Column(name = "stop_id", index = true, notNull = true)
    lateinit var stopId: String

    @Column(name = "stop_sequence", notNull = true)
    var sequence: Int = 0

    @Column(name = "stop_headsign")
    var headsign: String? = null

    @Column(name = "pickup_type")
    var pickupType: StopPickupType? = StopPickupType.REGULARLY

    @Column(name = "drop_off_type")
    var dropOffType: StopDropOffType? = StopDropOffType.REGULARLY

    @Column(name = "shape_dist_traveled", length = 10)
    var shapeDistTraveled: String? = null

    @Column
    var timepoint: TimepointType? = TimepointType.EXACT

    val trip: Trip by lazy {
        Select().from(Trip::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", tripId)
                .executeSingle() as Trip
    }

    val stop: Stop by lazy {
        Select().from(Stop::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", stopId)
                .executeSingle() as Stop
    }
}