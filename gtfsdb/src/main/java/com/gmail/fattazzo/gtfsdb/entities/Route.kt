package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import com.gmail.fattazzo.gtfsdb.entities.types.RouteType

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "routes", id = "_id")
class Route : BaseModel() {

    @Column(name = "route_id", index = true, notNull = true)
    lateinit var id: String

    @Column(name = "agency_id")
    var agencyId: String? = null

    @Column(name = "route_short_name", length = 50, notNull = true)
    lateinit var shortName: String

    @Column(name = "route_long_name", notNull = true)
    lateinit var longName: String

    @Column(name = "route_desc")
    var description: String? = null

    @Column(name = "route_type", notNull = true)
    var type: RouteType = RouteType.TRAM_STREETCAR_LIGHTRAIL

    @Column(name = "route_url")
    var url: String? = null

    @Column(name = "route_color", length = 10)
    var color: String? = null

    @Column(name = "route_text_color", length = 50)
    var textColor: String? = null

    @Column
    var sortOrder: Int? = null

    val agency: Agency? by lazy {
        Select().from(Agency::class.java)
                .where("feedId = ?", feedId)
                .and("id = ?", agencyId)
                .executeSingle() as Agency?
    }
}