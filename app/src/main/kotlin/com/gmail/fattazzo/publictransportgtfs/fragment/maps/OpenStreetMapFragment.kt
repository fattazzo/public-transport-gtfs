package com.gmail.fattazzo.publictransportgtfs.fragment.maps

import android.view.View
import com.gmail.fattazzo.gtfsdb.entities.Stop
import com.gmail.fattazzo.publictransportgtfs.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


/**
 * @author fattazzo
 *         <p/>
 *         date: 08/02/18
 */
@EFragment(R.layout.fragment_maps_openstreet)
open class OpenStreetMapFragment : BaseMapFargment(), MapView.OnFirstLayoutListener {

    @ViewById
    lateinit var mapView: MapView

    @AfterViews
    fun initView() {
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setBuiltInZoomControls(false)
        mapView.setMultiTouchControls(true)

        if (mapView.getScreenRect(null).height() == 0) {
            mapView.addOnFirstLayoutListener(this)
        } else {
            mapView.invalidate()

        }
    }

    override fun onFirstLayout(v: View?, left: Int, top: Int, right: Int, bottom: Int) {
        mapView.invalidate()

    }

    override fun addMarkers(stops: List<Stop>) {

        stops.forEach {
            val startPoint = GeoPoint(it.latitude.toDouble(), it.longitude.toDouble())
            val startMarker = Marker(mapView)
            startMarker.setPosition(startPoint)
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            startMarker.setIcon(resources.getDrawable(R.drawable.marker_default))
            startMarker.setTitle(it.name)
            startMarker.setSnippet(it.description)
            mapView.getOverlays().add(startMarker)
        }
        mapView.invalidate()
    }

    override fun zoomToBox(maxLatitude: Double, maxLongitude: Double, minLatitude: Double, minLongitude: Double) {
        val boundingBox = BoundingBox(maxLatitude, maxLongitude, minLatitude, minLongitude)
        mapView.zoomToBoundingBox(boundingBox, false)
        mapView.zoomToBoundingBox(boundingBox, false)
        mapView.invalidate()
    }

    override fun zoomToLocation(latitude: Double, longitude: Double) {
        mapView.controller.zoomTo(60)
        mapView.controller.animateTo(GeoPoint(latitude, longitude))
    }

    override fun removeMarkers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}