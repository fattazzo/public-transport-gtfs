package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select

/**
 * @author fattazzo
 *         <p/>
 *         date: 14/02/18
 */
@Table(name = "fare_attributes", id = "_id")
class FareAttribute : BaseModel() {

    @Column(name = "fare_id", notNull = true, index = true)
    lateinit var fareId: String

    @Column(notNull = true)
    lateinit var price: String

    @Column(name = "currency_type", notNull = true)
    lateinit var currencyType: String

    @Column(name = "payment_method", notNull = true)
    var paymentMethod: Int = 0

    @Column(notNull = true)
    lateinit var transfers: String

    @Column(name = "agency_id", length = 100, index = true)
    var agencyId: String? = null

    @Column(name = "transfer_duration")
    var transferDuration: String? = null

    val fareRule: FareRule by lazy {
        Select().from(FareRule::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", fareId)
                .executeSingle() as FareRule
    }

    val agency: Agency? by lazy {
        Select().from(Agency::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", fareId)
                .executeSingle() as Agency?
    }
}