package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select

/**
 * @author fattazzo
 *         <p/>
 *         date: 14/02/18
 */
@Table(name = "fare_rules", id = "_id")
class FareRule : BaseModel() {

    @Column(name = "fare_id", notNull = true, index = true)
    lateinit var id: String

    @Column(name = "route_id")
    var routeId: String? = null

    @Column(name = "origin_id")
    var originId: String? = null

    @Column(name = "destination_id")
    var destinationId: String? = null

    @Column(name = "contains_id")
    var containsId: String? = null

    val route: Route? by lazy {
        Select().from(Route::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", routeId)
                .executeSingle() as Route?
    }
}