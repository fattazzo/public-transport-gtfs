package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import com.gmail.fattazzo.gtfsdb.entities.types.DirectionType
import com.gmail.fattazzo.gtfsdb.entities.types.TripBikesAllowedType
import com.gmail.fattazzo.gtfsdb.entities.types.TripWheelchairAccessibleType

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "trips", id = "_id")
class Trip : BaseModel() {

    @Column(name = "trip_id", index = true, notNull = true)
    lateinit var id: String

    @Column(name = "route_id", index = true, notNull = true)
    lateinit var routeId: String

    @Column(name = "service_id", index = true, notNull = true)
    lateinit var serviceId: String

    @Column(name = "trip_headsign")
    var headsign: String? = null

    @Column(name = "trip_short_name", length = 100)
    var shortName: String? = null

    @Column(name = "direction_id")
    var directionType: DirectionType = DirectionType.OUTBOUND

    @Column(name = "block_id")
    var blockId: String? = null

    @Column(name = "shape_id")
    var shapeId: String? = null

    @Column(name = "wheelchair_accessible")
    var wheelchairAccessible: TripWheelchairAccessibleType = TripWheelchairAccessibleType.NO_INFORMATION

    @Column(name = "bikes_allowed")
    var bikesAllowed: TripBikesAllowedType? = TripBikesAllowedType.NO_INFORMATION

    val route: Route by lazy {
        Select().from(Route::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", routeId)
                .executeSingle() as Route
    }

    val shape: Shape? by lazy {
        Select().from(Shape::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", shapeId)
                .executeSingle() as Shape?
    }
}