package com.gmail.fattazzo.publictransportgtfs.transitfeed.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
class UrlInformation : Serializable {

    @SerializedName("i")
    var infoUrl: String? = null

    @SerializedName("d")
    var downloadUrl: String? = null
}