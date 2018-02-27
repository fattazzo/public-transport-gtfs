package com.gmail.fattazzo.feedsources.transitland.domain.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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