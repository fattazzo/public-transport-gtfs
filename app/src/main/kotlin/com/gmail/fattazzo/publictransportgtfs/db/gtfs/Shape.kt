package com.gmail.fattazzo.publictransportgtfs.db.gtfs

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

    @Column(index = true, notNull = true)
    lateinit var id: String

    @Column(notNull = true)
    var latitude: BigDecimal = BigDecimal.ZERO

    @Column(notNull = true)
    var longitude: BigDecimal = BigDecimal.ZERO

    @Column(notNull = true)
    lateinit var pointSequence: String

    @Column
    var distanceTraveled: String? = null
}