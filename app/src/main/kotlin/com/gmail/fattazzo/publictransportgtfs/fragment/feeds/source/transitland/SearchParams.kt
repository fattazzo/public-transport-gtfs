package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland

import com.gmail.fattazzo.feedsources.transitland.domain.Location
import java.io.Serializable

/**
 * @author fattazzo
 *         <p/>
 *         date: 05/02/18
 */
open class SearchParams : Serializable {

    var countryCode: String = ""

    var location: Location? = null

    var name: String? = null

    var offset: Int = 0

    var perPage: Int = 50

    override fun toString(): String {
        return "SearchParams(countryCode='$countryCode', location=$location, name=$name, offset=$offset, perPage=$perPage)"
    }
}