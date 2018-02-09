package com.gmail.fattazzo.publictransportgtfs.feeds.source

import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Feed
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Operator
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GeoJson : Serializable {

    @SerializedName("type")
    var type: String = "Feature"

    @SerializedName("geometry")
    var geometry: Geometry? = null

    @SerializedName("properties")
    var properties: Properties = Properties()

    companion object {

        fun fromFeed(feed: Feed): GeoJson {
            val geoJson = GeoJson()
            geoJson.geometry = feed.geometry
            geoJson.properties.name = feed.name ?: feed.onestopId.orEmpty()
            return geoJson
        }

        fun fromOperator(operator: Operator): GeoJson {
            val geoJson = GeoJson()
            geoJson.geometry = operator.geometry
            geoJson.properties.name = operator.name.orEmpty()
            return geoJson
        }
    }
}