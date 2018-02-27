package com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson

import com.gmail.fattazzo.feedsources.GeoJson
import com.gmail.fattazzo.feedsources.GeoJsonFeatures
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.google.gson.GsonBuilder
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.FragmentArg
import org.androidannotations.annotations.InstanceState


/**
 * @author fattazzo
 *         <p/>
 *         date: 09/02/18
 */
@EFragment
abstract class GeoJsonMapFragment : BaseFragment() {

    @JvmField
    @InstanceState
    @FragmentArg
    var geoJson: GeoJson? = null

    @JvmField
    @InstanceState
    @FragmentArg
    var geoJsonFeatures: GeoJsonFeatures? = null

    private fun geoJsonAsString(geoJsonObject: GeoJson? = geoJson): String {
        return GsonBuilder().setPrettyPrinting().create().toJson(geoJsonObject)
    }

    private fun geoJsonFeaturesAsString(): List<String> {
        return geoJsonFeatures?.features?.map { geoJson -> geoJsonAsString(geoJson) }.orEmpty()
    }

    protected abstract fun showGeoJsonObject(geoJson: String = geoJsonAsString())

    protected abstract fun showGeoJsonFeatures(geoJsonFeatures: List<String> = geoJsonFeaturesAsString())
}