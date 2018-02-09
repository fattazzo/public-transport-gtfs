package com.gmail.fattazzo.publictransportgtfs.fragment.maps

import com.gmail.fattazzo.publictransportgtfs.feeds.source.GeoJson
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.GeoJsonMapFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.google.GoogleMapGeoJsonFragment_
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.openstreet.OpenStreetGeoJsonFragment_

/**
 * @author fattazzo
 *         <p/>
 *         date: 09/02/18
 */
object FragmentMapsSelector {

    fun getGeoJsonFragment(type: Int, geoJson: GeoJson): GeoJsonMapFragment {
        return when (type) {
            0 -> OpenStreetGeoJsonFragment_.builder().geoJson(geoJson).build()
            1 -> GoogleMapGeoJsonFragment_.builder().geoJson(geoJson).build()
            else -> OpenStreetGeoJsonFragment_.builder().geoJson(geoJson).build()
        }
    }

}