package com.gmail.fattazzo.publictransportgtfs.db.gtfs

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import com.gmail.fattazzo.gtfs.domain.type.DirectionType
import com.gmail.fattazzo.gtfs.domain.type.TripBikesAllowedType
import com.gmail.fattazzo.gtfs.domain.type.TripWheelchairAccessibleType

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "trips", id = "_id")
class Trip : BaseModel() {

    @Column(index = true, notNull = true)
    lateinit var id: String

    @Column(index = true, notNull = true)
    lateinit var routeId: String

    @Column(index = true, notNull = true)
    lateinit var serviceId: String

    @Column
    var headsign: String? = null

    @Column(length = 100)
    var shortName: String? = null

    @Column
    var directionType: DirectionType = DirectionType.OUTBOUND

    @Column
    var blockId: String? = null

    @Column
    var shapeId: String? = null

    @Column
    var wheelchairAccessible: TripWheelchairAccessibleType = TripWheelchairAccessibleType.NO_INFORMATION

    @Column
    var bikesAllowed: TripBikesAllowedType? = TripBikesAllowedType.NO_INFORMATION

    val route: Route by lazy {
        Select().from(Route::class.java)
                .where("agencyRef = ?", agencyRef)
                .and("id = ?", routeId)
                .executeSingle() as Route
    }

    val shape: Shape? by lazy {
        Select().from(Shape::class.java)
                .where("agencyRef = ?", agencyRef)
                .and("id = ?", shapeId)
                .executeSingle() as Shape?
    }
}