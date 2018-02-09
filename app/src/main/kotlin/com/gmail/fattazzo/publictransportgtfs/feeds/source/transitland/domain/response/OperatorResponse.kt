package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.response

import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Operator
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
class OperatorResponse {

    class Meta : Serializable {
        @SerializedName("sort_key")
        lateinit var sortKey: String

        @SerializedName("sort_order")
        lateinit var sortOrder: String

        @SerializedName("per_page")
        var perPage: Int = 0

        @SerializedName("offset")
        var offset: Int = 0

        @SerializedName("prev")
        val prev: String? = null

        @SerializedName("next")
        val next: String? = null
    }

    @SerializedName("operators")
    val operators: List<Operator>? = null

    @SerializedName("meta")
    lateinit var meta: Meta
}