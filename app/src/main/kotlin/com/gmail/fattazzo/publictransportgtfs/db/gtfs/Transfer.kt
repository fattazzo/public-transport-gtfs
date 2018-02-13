package com.gmail.fattazzo.publictransportgtfs.db.gtfs

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import com.gmail.fattazzo.gtfs.domain.type.TransferType

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "transfers", id = "_id")
class Transfer : BaseModel() {

    @Column(notNull = true)
    lateinit var fromStopId: String

    @Column(notNull = true)
    lateinit var toStopId: String

    @Column(notNull = true)
    var type: TransferType = TransferType.RECOMMENDED

    @Column
    var minTransferTime: Int? = null

    val fromStop: Stop by lazy {
        Select().from(Stop::class.java)
                .where("agencyRef = ?", agencyRef)
                .and("id = ?", fromStopId)
                .executeSingle() as Stop
    }

    val toStop: Stop by lazy {
        Select().from(Stop::class.java)
                .where("agencyRef = ?", agencyRef)
                .and("id = ?", toStopId)
                .executeSingle() as Stop
    }
}