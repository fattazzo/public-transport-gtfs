package com.gmail.fattazzo.publictransportgtfs.transitfeed.domain

import com.google.gson.annotations.SerializedName


/**
 * @author fattazzo
 *         <p/>
 *         date: 24/01/18
 */
class LocationResponse {

    class Result {
        val locations: List<Location>? = null
    }

    @SerializedName("results")
    val result: Result? = null

    @SerializedName("status")
    val status: String? = null

    @SerializedName("ts")
    val timestamp: Int? = null
}