package com.gmail.fattazzo.publictransportgtfs.transitfeed.domain

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

/**
 * @author fattazzo
 *         <p/>
 *         date: 24/01/18
 */
class Location {

    @SerializedName("id")
    var id: Int = -1

    @SerializedName("pid")
    var parentId: Int = 0

    @SerializedName("n")
    var name: String = ""

    @SerializedName("t")
    var longName: String = ""

    @SerializedName("lat")
    var latitude: BigDecimal = BigDecimal.ZERO
    @SerializedName("lng")
    var longitude: BigDecimal = BigDecimal.ZERO
}