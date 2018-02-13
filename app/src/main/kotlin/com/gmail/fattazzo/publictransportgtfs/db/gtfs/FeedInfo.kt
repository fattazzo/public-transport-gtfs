package com.gmail.fattazzo.publictransportgtfs.db.gtfs

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import java.util.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "feed_infos", id = "_id")
class FeedInfo : BaseModel() {

    @Column(notNull = true)
    lateinit var publisherName: String

    @Column(notNull = true)
    lateinit var publisherUrl: String

    @Column(length = 20)
    var lang: String? = null

    @Column
    var startDate: Date? = null

    @Column
    var endDate: Date? = null

    @Column(length = 50)
    var version: String? = null
}