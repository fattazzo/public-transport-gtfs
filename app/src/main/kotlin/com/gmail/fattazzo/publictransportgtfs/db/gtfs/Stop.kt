package com.gmail.fattazzo.publictransportgtfs.db.gtfs

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.gmail.fattazzo.gtfs.domain.type.StopLocationType
import java.math.BigDecimal

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "stops", id = "_id")
class Stop : BaseModel() {

    @Column(index = true, notNull = true)
    lateinit var id: String

    @Column
    var code: String? = null

    @Column(notNull = true)
    lateinit var name: String

    @Column
    var description: String? = null

    @Column(notNull = true)
    var latitude: BigDecimal = BigDecimal.ZERO

    @Column(notNull = true)
    var longitude: BigDecimal = BigDecimal.ZERO

    @Column
    var zoneId: String? = null

    @Column
    var url: String? = null

    @Column
    var locationType: StopLocationType? = null

    @Column(length = 5)
    var parentStation: String? = null

    @Column(length = 50)
    var timezone: String? = null

    @Column(length = 5)
    var wheelchairBoarding: String? = null
}