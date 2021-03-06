package com.gmail.fattazzo.publictransportgtfs.activity.maps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.gmail.fattazzo.feedsources.GeoJson
import com.gmail.fattazzo.feedsources.GeoJsonFeatures
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.FragmentMapsSelector
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.Extra


@EActivity(R.layout.activity_maps_geojson)
open class GeoJsonMapsActivity : AppCompatActivity() {

    @JvmField
    @Extra
    var geoJson: GeoJson? = null

    @JvmField
    @Extra
    var geoJsonFeatures: GeoJsonFeatures? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            MaterialDialog.Builder(this)
                    .title(R.string.select_map_provider)
                    .items(R.array.map_providers)
                    .itemsCallbackSingleChoice(0) { _, _, which, _ ->
                        val fragment = FragmentMapsSelector.getGeoJsonFragment(which, geoJson, geoJsonFeatures)
                        FragmentUtils.replace(this@GeoJsonMapsActivity, fragment, containerResId = R.id.mapContainer)
                        true
                    }
                    .positiveText(R.string.confirm)
                    .show()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
