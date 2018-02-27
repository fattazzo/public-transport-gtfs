package com.gmail.fattazzo.feedsources.transitland.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Tag : Serializable {

    @SerializedName("agency_id")
    var agencyId: String? = null

    @SerializedName("agency_lang")
    var agencyLang: String? = null

    @SerializedName("agency_phone")
    var agencyPhone: String? = null

    @SerializedName("agency_fare_url")
    var agencyFareUrl: String? = null

}