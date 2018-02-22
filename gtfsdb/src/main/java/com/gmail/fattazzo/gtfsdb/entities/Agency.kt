package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "agency", id = "_id")
class Agency : BaseModel() {

    @Column(name = "agency_id", length = 100, index = true, notNull = true)
    lateinit var id: String

    @Column(name = "agency_name", notNull = true)
    lateinit var name: String

    @Column(name = "agency_url", notNull = true)
    lateinit var url: String

    @Column(name = "agency_timezone", length = 50, notNull = true)
    lateinit var timezone: String

    @Column(name = "agency_phone", length = 100)
    var phone: String? = null

    @Column(name = "agency_lang", length = 5)
    var lang: String? = null

    @Column(name = "agency_fare_url", length = 100)
    var fareUrl: String? = null

    @Column(name = "agency_email", length = 100)
    var email: String? = null
}