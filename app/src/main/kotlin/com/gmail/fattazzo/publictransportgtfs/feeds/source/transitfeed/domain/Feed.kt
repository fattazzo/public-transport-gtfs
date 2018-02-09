package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
class Feed : Serializable {

    @SerializedName("id")
    lateinit var id: String

    @SerializedName("ty")
    lateinit var type: String

    @SerializedName("t")
    lateinit var title: String

    @SerializedName("u")
    var urlInformation: UrlInformation? = null

    @SerializedName("latest")
    var latestUrl: LatestUrl? = null

    @SerializedName("l")
    lateinit var location: Location

    var versions: List<FeedVersion> = listOf()
}