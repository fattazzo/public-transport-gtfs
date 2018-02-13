package com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.google

import android.support.v4.graphics.ColorUtils
import com.afollestad.materialdialogs.MaterialDialog
import com.github.clans.fab.FloatingActionMenu
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.GeoJsonMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.google.maps.android.data.geojson.GeoJsonPolygon
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import org.json.JSONException
import org.json.JSONObject


/**
 * @author fattazzo
 *         <p/>
 *         date: 08/02/18
 */
@EFragment(R.layout.fragment_maps_google)
open class GoogleMapGeoJsonFragment : GeoJsonMapFragment(), OnMapReadyCallback {

    @ViewById
    lateinit var menuFab: FloatingActionMenu

    private var mMap: GoogleMap? = null

    @AfterViews
    fun init() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (geoJson != null) {
            showGeoJsonObject()
        }
    }

    private fun addColorsToLayer(layer: GeoJsonLayer) {
        val geoJsonPolygonStyle = layer.defaultPolygonStyle;
        geoJsonPolygonStyle.strokeColor = resources.getColor(android.R.color.holo_blue_dark)
        geoJsonPolygonStyle.fillColor = ColorUtils.setAlphaComponent(resources.getColor(android.R.color.holo_blue_light), 90)
    }

    @Click
    fun mapTypesFabClicked() {
        MaterialDialog.Builder(context!!)
                .title("[ToDo] Seleziona il tipo di mappa")
                .items("NORMAL", "SATELLITE", "HYBRID", "TERRAIN")
                .itemsCallbackSingleChoice(0) { _, _, which, _ ->
                    when (which) {
                        0 -> mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
                        1 -> mMap!!.mapType = GoogleMap.MAP_TYPE_SATELLITE
                        2 -> mMap!!.mapType = GoogleMap.MAP_TYPE_HYBRID
                        3 -> mMap!!.mapType = GoogleMap.MAP_TYPE_TERRAIN
                        else -> mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
                    }
                    menuFab.close(true)
                    true
                }
                .positiveText("[ToDo] choose")
                .show()
    }

    override fun showGeoJsonObject(geoJson: String) {
        try {
            val layer = GeoJsonLayer(mMap, JSONObject(geoJson))
            addColorsToLayer(layer)
            layer.addLayerToMap()

            val builder = LatLngBounds.builder()

            layer.features
                    .filter { it.hasGeometry() }
                    .map { it.geometry }
                    .map { (it as GeoJsonPolygon).coordinates }
                    .forEach { lists -> lists.flatMap { it }.forEach { builder.include(it) } }

            val bb = builder.build()
            mMap!!.moveCamera(CameraUpdateFactory.newLatLngBounds(bb, 50))
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    override fun showGeoJsonFeatures(geoJsonFeatures: List<String>) {
        val builder = LatLngBounds.builder()

        geoJsonFeatures.forEach { geoJsonString ->
            run {
                try {
                    val layer = GeoJsonLayer(mMap, JSONObject(geoJsonString))
                    layer.addLayerToMap()

                    layer.features
                            .filter { it.hasGeometry() }
                            .map { it.geometry }
                            .map { (it as GeoJsonPolygon).coordinates }
                            .forEach { lists -> lists.flatMap { it }.forEach { builder.include(it) } }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }

        val bb = builder.build()
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngBounds(bb, 50))
    }
}