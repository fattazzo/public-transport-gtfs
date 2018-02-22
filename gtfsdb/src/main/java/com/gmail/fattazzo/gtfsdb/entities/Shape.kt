package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import java.math.BigDecimal

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "shapes", id = "_id")
class Shape : BaseModel() {

    @Column(name = "shape_id", index = true, notNull = true)
    lateinit var id: String

    @Column(name = "shape_pt_lat", notNull = true)
    var latitude: BigDecimal = BigDecimal.ZERO

    @Column(name = "shape_pt_lon", notNull = true)
    var longitude: BigDecimal = BigDecimal.ZERO

    @Column(name = "shape_pt_sequence", notNull = true)
    lateinit var pointSequence: String

    @Column(name = "shape_dist_traveled")
    var distanceTraveled: String? = null
}