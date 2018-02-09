package com.gmail.fattazzo.publictransportgtfs.feeds.source

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author fattazzo
 *         <p/>
 *         date: 08/02/18
 */
class Properties : Serializable {

    @SerializedName("name")
    var name: String = ""
}