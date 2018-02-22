package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import java.util.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "feed_info", id = "_id")
class FeedInfo : BaseModel() {

    @Column(name = "feed_publisher_name", notNull = true)
    lateinit var publisherName: String

    @Column(name = "feed_publisher_url", notNull = true)
    lateinit var publisherUrl: String

    @Column(name = "feed_lang", length = 20)
    var lang: String? = null

    @Column(name = "feed_start_date")
    var startDate: Date? = null

    @Column(name = "feed_end_date")
    var endDate: Date? = null

    @Column(name = "feed_version", length = 50)
    var version: String? = null
}