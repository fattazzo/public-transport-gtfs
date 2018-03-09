package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import com.gmail.fattazzo.gtfsdb.entities.types.StopLocationType
import java.math.BigDecimal

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "stops", id = "_id")
class Stop : BaseModel() {

    @Column(name = "stop_id", index = true, notNull = true)
    lateinit var id: String

    @Column(name = "stop_code")
    var code: String? = null

    @Column(name = "stop_name", notNull = true)
    lateinit var name: String

    @Column(name = "stop_desc")
    var description: String? = null

    @Column(name = "stop_lat", notNull = true)
    var latitude: BigDecimal = BigDecimal.ZERO

    @Column(name = "stop_lon", notNull = true)
    var longitude: BigDecimal = BigDecimal.ZERO

    @Column(name = "zone_id")
    var zoneId: String? = null

    @Column(name = "stop_url")
    var url: String? = null

    @Column(name = "location_type")
    var locationType: StopLocationType? = null

    @Column(name = "parent_station", length = 5)
    var parentStation: String? = null

    @Column(name = "stop_timezone", length = 50)
    var timezone: String? = null

    @Column(name = "wheelchair_boarding", length = 5)
    var wheelchairBoarding: String? = null

    // Transient properties
    var selected: Boolean = false

    companion object {

        fun search(value: String): MutableList<Stop>? {
            return Select()
                    .from(Stop::class.java)
                    .where("stop_name like(?)", "%" + value + "%")
                    .or("stop_desc like(?)", "%" + value + "%")
                    .execute<Stop>()
        }
    }
}