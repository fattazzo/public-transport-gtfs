package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import com.gmail.fattazzo.gtfsdb.entities.types.TransferType

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "transfers", id = "_id")
class Transfer : BaseModel() {

    @Column(name = "from_stop_id", notNull = true)
    lateinit var fromStopId: String

    @Column(name = "to_stop_id", notNull = true)
    lateinit var toStopId: String

    @Column(name = "transfer_type", notNull = true)
    var type: TransferType = TransferType.RECOMMENDED

    @Column(name = "min_transfer_time")
    var minTransferTime: Int? = null

    val fromStop: Stop by lazy {
        Select().from(Stop::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", fromStopId)
                .executeSingle() as Stop
    }

    val toStop: Stop by lazy {
        Select().from(Stop::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", toStopId)
                .executeSingle() as Stop
    }
}