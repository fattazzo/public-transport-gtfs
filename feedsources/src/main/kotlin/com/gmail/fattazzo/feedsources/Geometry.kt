package com.gmail.fattazzo.feedsources

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Geometry : Serializable {

    @SerializedName("type")
    var type: String? = null

    @SerializedName("coordinates")
    var coordinates: List<List<List<Float>>>? = null

}