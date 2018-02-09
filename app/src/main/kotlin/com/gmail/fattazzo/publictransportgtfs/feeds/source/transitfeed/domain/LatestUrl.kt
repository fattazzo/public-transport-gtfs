package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain

import com.google.gson.annotations.SerializedName

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
class LatestUrl {

    @SerializedName("ts")
    var timestamp: String? = null
}