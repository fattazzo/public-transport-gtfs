package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author fattazzo
 *         <p/>
 *         date: 06/02/18
 */
class Location : Serializable {

    @SerializedName("code")
    lateinit var code: String

    @SerializedName("subdivision_name")
    lateinit var name: String

    @SerializedName("country_code")
    lateinit var countryCode: String

    override fun toString(): String {
        return "Location(code='$code', name='$name', countryCode='$countryCode')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}