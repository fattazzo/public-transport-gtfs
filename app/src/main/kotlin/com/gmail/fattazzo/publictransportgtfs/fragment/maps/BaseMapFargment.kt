package com.gmail.fattazzo.publictransportgtfs.fragment.maps

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.github.clans.fab.FloatingActionMenu
import com.gmail.fattazzo.gtfsdb.entities.BaseModel
import com.gmail.fattazzo.gtfsdb.entities.Stop
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.BaseFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.search.stops.SearchStopsFragment
import com.gmail.fattazzo.publictransportgtfs.fragment.search.stops.SearchStopsFragment_
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import com.gmail.fattazzo.publictransportgtfs.utils.PermissionsUtil
import org.androidannotations.annotations.*


/**
 * @author fattazzo
 *         <p/>
 *         date: 03/03/18
 */
@EFragment
open abstract class BaseMapFargment : BaseFragment(), LocationListener {

    @ViewById
    lateinit var menuFab: FloatingActionMenu

    @SystemService
    lateinit var locationManager: LocationManager

    @JvmField
    @InstanceState
    protected var stops = arrayListOf<Stop>()

    @JvmField
    var showStops = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
    }

    @AfterViews
    fun init() {
        if (ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity!!, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    PermissionsUtil.PERMISSION_ALL);
        }

        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10f, this)
    }

    @Background(serial = "loadData")
    open fun loadMarkers() {
        if (stops.isEmpty()) {
            stops.addAll(BaseModel.loadAll(Stop::class.java))
        }

        if (showStops) {
            addMarkers(stops)
        }
    }

    open fun toggleStops() {
        showStops = !showStops

        if (showStops) loadMarkers() else removeMarkers()
    }

    @UiThread
    abstract fun addMarkers(stops: List<Stop>)

    @UiThread
    abstract fun removeMarkers()

    @UiThread
    abstract fun zoomToLocation(latitude: Double, longitude: Double)

    @UiThread
    abstract fun zoomToBox(maxLatitude: Double, maxLongitude: Double, minLatitude: Double, minLongitude: Double)

    @Background(serial = "loadData")
    open fun zoomToStops() {
        if (stops.isEmpty()) {
            stops.addAll(BaseModel.loadAll(Stop::class.java))
        }

        var minLat = Double.MAX_VALUE
        var maxLat = Double.MIN_VALUE
        var minLong = Double.MAX_VALUE
        var maxLong = Double.MIN_VALUE
        stops.forEach {
            if (it.latitude.toDouble() < minLat)
                minLat = it.latitude.toDouble()
            if (it.latitude.toDouble() > maxLat)
                maxLat = it.latitude.toDouble()
            if (it.longitude.toDouble() < minLong)
                minLong = it.longitude.toDouble()
            if (it.longitude.toDouble() > maxLong)
                maxLong = it.longitude.toDouble()
        }
        zoomToBox(maxLat, maxLong, minLat, minLong)
    }

    override fun onLocationChanged(p0: Location?) {
        if (p0 != null) {
            zoomToLocation(p0.latitude, p0.longitude)
        }
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    open fun stopsLoaded(): Boolean {
        return stops.isNotEmpty()
    }

    @Click
    open fun stopsSearchFabClicked() {
        menuFab.close(false)
        val searchStopsFragment = SearchStopsFragment_.builder().build()
        searchStopsFragment.setTargetFragment(this,SearchStopsFragment.SEARCH_RESULT)
        FragmentUtils.add(activity, searchStopsFragment, containerResId = R.id.mapContainer, animationType = FragmentUtils.AnimationType.RIGHT_TO_LEFT)
    }

    @OnActivityResult(SearchStopsFragment.SEARCH_RESULT)
    fun onResult(data: Intent) {
        val stopsExtra = data.getSerializableExtra(SearchStopsFragment.EXTRA_STOPS) as ArrayList<Stop>?
        stopsExtra.let {
            addMarkers(stopsExtra!!.toList())
            zoomToStops()
        }
    }

    override fun backPressed(): Boolean {
        activity?.finish()
        return true
    }
}