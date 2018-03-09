package com.gmail.fattazzo.publictransportgtfs.fragment.maps

import com.gmail.fattazzo.feedsources.GeoJson
import com.gmail.fattazzo.feedsources.GeoJsonFeatures
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.GeoJsonMapFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.google.GoogleMapGeoJsonFragment_
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.openstreet.OpenStreetGeoJsonFragment_

/**
 * @author fattazzo
 *         <p/>
 *         date: 09/02/18
 */
object FragmentMapsSelector {

    fun getGeoJsonFragment(type: Int, geoJson: GeoJson?, geoJsonFeatures: GeoJsonFeatures?): GeoJsonMapFragment {
        return when (type) {
            0 -> OpenStreetGeoJsonFragment_.builder().geoJson(geoJson).geoJsonFeatures(geoJsonFeatures).build()
            1 -> GoogleMapGeoJsonFragment_.builder().geoJson(geoJson).geoJsonFeatures(geoJsonFeatures).build()
            else -> OpenStreetGeoJsonFragment_.builder().geoJson(geoJson).geoJsonFeatures(geoJsonFeatures).build()
        }
    }

    fun getFragment(type: Int): BaseMapFargment {
        return when (type) {
            0 -> OpenStreetMapFragment_.builder().build()
            1 -> GoogleMapFragment_.builder().build()
            else -> OpenStreetMapFragment_.builder().build()
        }
    }

}