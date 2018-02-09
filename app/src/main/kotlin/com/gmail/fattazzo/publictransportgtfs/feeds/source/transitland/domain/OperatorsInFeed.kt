package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OperatorsInFeed : Serializable {

    @SerializedName("gtfs_agency_id")
    var gtfsAgencyId: String? = null

    @SerializedName("operator_onestop_id")
    var operatorOnestopId: String? = null

    @SerializedName("feed_onestop_id")
    var feedOnestopId: String? = null

    @SerializedName("operator_url")
    var operatorUrl: String? = null

    @SerializedName("feed_url")
    var feedUrl: String? = null
}