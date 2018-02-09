package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain.response

import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain.Feed
import com.google.gson.annotations.SerializedName

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
class FeedResponse {

    class Result {
        val total: Int? = null
        val feeds: List<Feed>? = null
    }

    @SerializedName("results")
    val result: Result? = null

    @SerializedName("status")
    val status: String? = null

    @SerializedName("ts")
    val timestamp: Int? = null
}