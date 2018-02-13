package com.gmail.fattazzo.publictransportgtfs.db.gtfs

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "agencies", id = "_id")
class Agency : BaseModel() {

    @Column(length = 100, index = true, notNull = true)
    lateinit var id: String

    @Column(notNull = true)
    lateinit var name: String

    @Column(notNull = true)
    lateinit var url: String

    @Column(length = 50, notNull = true)
    lateinit var timezone: String

    @Column(length = 100)
    var phone: String? = null

    @Column(length = 5)
    var lang: String? = null

    @Column(length = 100)
    var fareUrl: String? = null

    @Column(length = 100)
    var email: String? = null
}