package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import java.text.SimpleDateFormat
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
    private var startDateString: String? = null

    @Column(name = "feed_end_date")
    private var endDateString: String? = null

    @Column(name = "feed_version", length = 50)
    var version: String? = null

    val startDate : Date? by lazy {
        if(startDateString != null) {
             SimpleDateFormat("yyyyMMdd").parse(startDateString)
        } else {
            null
        }
    }

    val endDate : Date? by lazy {
        if(endDateString != null) {
            SimpleDateFormat("yyyyMMdd").parse(endDateString)
        } else {
            null
        }
    }
}