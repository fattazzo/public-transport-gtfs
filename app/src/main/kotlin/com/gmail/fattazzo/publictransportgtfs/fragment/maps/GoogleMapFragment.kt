package com.gmail.fattazzo.publictransportgtfs.fragment.maps

import com.gmail.fattazzo.gtfsdb.entities.Stop
import com.gmail.fattazzo.publictransportgtfs.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment


/**
 * @author fattazzo
 *         <p/>
 *         date: 08/02/18
 */
@EFragment(R.layout.fragment_maps_google)
open class GoogleMapFragment : BaseMapFargment(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    private val markers = arrayListOf<Marker>()

    @AfterViews
    fun initViews() {
        menuFab.hideMenu(false)
        menuFab.setClosedOnTouchOutside(true)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        menuFab.showMenu(true)

        if (!stopsLoaded()) {
            loadMarkers()
            zoomToStops()
        }
    }

    override fun addMarkers(stops: List<Stop>) {
        stops.forEach {
            val marker = mMap!!.addMarker(MarkerOptions()
                    .position(LatLng(it.latitude.toDouble(), it.longitude.toDouble()))
                    .title(it.name)
                    .snippet(it.description))
            markers.add(marker)
        }
    }

    override fun removeMarkers() {
        markers.forEach {
            it.remove()
        }
        markers.clear()
    }

    override fun zoomToBox(maxLatitude: Double, maxLongitude: Double, minLatitude: Double, minLongitude: Double) {
        val builder = LatLngBounds.Builder()
        builder.include(LatLng(maxLatitude, maxLongitude))
        builder.include(LatLng(minLatitude, minLongitude))
        val bounds = builder.build()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 20)
        mMap!!.animateCamera(cu)
    }

    override fun zoomToLocation(latitude: Double, longitude: Double) {
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(latitude, longitude)))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
    }

    @Click
    fun stopsToggleFabClicked() {
        toggleStops()
        menuFab.close(true)
    }
}