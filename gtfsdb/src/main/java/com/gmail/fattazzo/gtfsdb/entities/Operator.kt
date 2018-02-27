package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import java.io.Serializable

@Table(name = "operator")
class Operator : Model(), Serializable {

    @Column(notNull = true)
    lateinit var onestopId: String

    @Column
    var name: String? = null

    @Column
    var shortName: String? = null

    @Column
    var website: String? = null

    @Column
    var country: String? = null

    @Column
    var state: String? = null

    @Column
    var metro: String? = null

    companion object {

        fun load(): Operator? {
            return Select().from(Operator::class.java).executeSingle()
        }

        fun build(country: String?, metro: String?, name: String?, shortName: String?,
                  uniqueId: String, state: String?, website: String?): Operator {
            val operator = Operator()
            operator.country = country
            operator.metro = metro
            operator.name = name
            operator.onestopId = uniqueId
            operator.shortName = shortName
            operator.state = state
            operator.website = website
            return operator
        }
    }
}