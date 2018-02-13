package com.gmail.fattazzo.publictransportgtfs.feeds.source

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GeoJsonFeatures : Serializable {

    @SerializedName("type")
    var type: String? = null

    @SerializedName("features")
    var features: List<GeoJson>? = null

}