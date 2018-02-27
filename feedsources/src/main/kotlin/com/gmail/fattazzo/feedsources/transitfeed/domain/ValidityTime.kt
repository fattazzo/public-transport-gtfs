package com.gmail.fattazzo.feedsources.transitfeed.domain

import com.google.gson.annotations.SerializedName

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
class ValidityTime {

    @SerializedName("s")
    var start: String? = null

    @SerializedName("f")
    var end: String? = null
}