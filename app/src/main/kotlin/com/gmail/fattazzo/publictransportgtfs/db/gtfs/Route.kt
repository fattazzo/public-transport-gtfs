package com.gmail.fattazzo.publictransportgtfs.db.gtfs

import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select
import com.gmail.fattazzo.gtfs.domain.type.RouteType

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@Table(name = "routes", id = "_id")
class Route : BaseModel() {

    @Column(index = true, notNull = true)
    lateinit var id: String

    @Column(name = "agency_id")
    var agencyId: String? = null

    @Column(length = 50, notNull = true)
    lateinit var shortName: String

    @Column(notNull = true)
    lateinit var longName: String

    @Column
    var description: String? = null

    @Column(notNull = true)
    var type: RouteType = RouteType.TRAM_STREETCAR_LIGHTRAIL

    @Column
    var url: String? = null

    @Column(length = 10)
    var color: String? = null

    @Column(length = 50)
    var textColor: String? = null

    @Column
    var sortOrder: Int? = null

    val agency: Agency? by lazy {
        Select().from(Agency::class.java)
                .where("agencyRef = ?", agencyRef)
                .and("id = ?", agencyId)
                .executeSingle() as Agency?
    }
}