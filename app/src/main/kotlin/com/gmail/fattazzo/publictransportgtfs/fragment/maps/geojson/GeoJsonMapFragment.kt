package com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson

import com.gmail.fattazzo.publictransportgtfs.feeds.source.GeoJson
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
open class GeoJsonMapFragment : BaseFragment() {

    @InstanceState
    @FragmentArg
    lateinit var geoJson: GeoJson

    protected fun geoJsonAsString(): String {
        return GsonBuilder().setPrettyPrinting().create().toJson(geoJson)
    }
}