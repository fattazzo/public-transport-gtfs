package com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.openstreet

import android.graphics.drawable.BitmapDrawable
import android.view.View
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.geojson.GeoJsonMapFragment
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import org.osmdroid.bonuspack.kml.KmlDocument
import org.osmdroid.bonuspack.kml.Style
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay


/**
 * @author fattazzo
 *         <p/>
 *         date: 08/02/18
 */
@EFragment(R.layout.fragment_maps_openstreet)
open class OpenStreetGeoJsonFragment : GeoJsonMapFragment(), MapView.OnFirstLayoutListener {

    @ViewById
    lateinit var mapView: MapView

    private var mInitialBoundingBox: BoundingBox? = null

    @AfterViews
    fun init() {
        showGeoJsonObject()
    }

    override fun onFirstLayout(v: View?, left: Int, top: Int, right: Int, bottom: Int) {
        if (mInitialBoundingBox != null) {
            mapView.zoomToBoundingBox(mInitialBoundingBox, false);
            mapView.zoomToBoundingBox(mInitialBoundingBox, false);
            mapView.invalidate()
        }
    }

    override fun showGeoJsonObject(geoJson: String) {
        val kmlDocument = KmlDocument()
        kmlDocument.parseGeoJSON(geoJson)

        val defaultMarker = resources.getDrawable(R.drawable.marker_default)

        val defaultBitmap = (defaultMarker as BitmapDrawable).bitmap

        val defaultStyle = Style(defaultBitmap, -0x6fefef56, 5f, 0x20AA1010)

        val geoJsonOverlay = kmlDocument.mKmlRoot.buildOverlay(mapView, defaultStyle, null, kmlDocument) as FolderOverlay

        mapView.overlayManager.clear()
        mapView.overlays.clear()
        mapView.overlays.add(geoJsonOverlay)

        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setBuiltInZoomControls(false)
        mapView.setMultiTouchControls(true)

        val bb = kmlDocument.mKmlRoot.boundingBox
        if (bb != null) {
            //map.zoomToBoundingBox(bb, false); //=> not working in onCreate - this is a well-known osmdroid issue.
            //Workaround:
            if (mapView.getScreenRect(null).height() == 0) {
                mInitialBoundingBox = bb;
                mapView.addOnFirstLayoutListener(this);
            } else {
                mapView.zoomToBoundingBox(bb, false);
                mapView.zoomToBoundingBox(bb, false);
                mapView.invalidate()
            }
        }
    }

    override fun showGeoJsonFeatures(geoJsonFeatures: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}