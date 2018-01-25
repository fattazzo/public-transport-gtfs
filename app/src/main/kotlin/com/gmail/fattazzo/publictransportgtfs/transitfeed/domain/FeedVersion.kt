package com.gmail.fattazzo.publictransportgtfs.transitfeed.domain

import com.google.gson.annotations.SerializedName

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
class FeedVersion {

    @SerializedName("id")
    lateinit var id: String

    @SerializedName("f")
    lateinit var feed: Feed

    @SerializedName("ts")
    var timestamp: Int = 0

    @SerializedName("size")
    var fileSize: Int = 0

    @SerializedName("url")
    lateinit var url: String

    @SerializedName("d")
    var validityTime: ValidityTime? = null

    var import: Boolean = true
}