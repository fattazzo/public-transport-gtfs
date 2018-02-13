package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.response

import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Operator
import com.google.gson.annotations.SerializedName

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
class OperatorResponse {

    @SerializedName("operators")
    val operators: List<Operator>? = null

    @SerializedName("meta")
    lateinit var meta: Meta
}