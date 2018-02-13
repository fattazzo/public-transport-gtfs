package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.response

import com.gmail.fattazzo.publictransportgtfs.feeds.source.GeoJson
import com.google.gson.annotations.SerializedName

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
class GeoJsonFetauresResponse {

    @SerializedName("features")
    val features: List<GeoJson>? = null

    @SerializedName("meta")
    lateinit var meta: Meta
}