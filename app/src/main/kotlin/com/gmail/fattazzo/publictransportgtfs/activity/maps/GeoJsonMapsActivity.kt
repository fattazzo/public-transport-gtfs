package com.gmail.fattazzo.publictransportgtfs.activity.maps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.feeds.source.GeoJson
import com.gmail.fattazzo.publictransportgtfs.feeds.source.GeoJsonFeatures
import com.gmail.fattazzo.publictransportgtfs.fragment.maps.FragmentMapsSelector
import com.gmail.fattazzo.publictransportgtfs.utils.FragmentUtils
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.Extra


@EActivity(R.layout.activity_maps)
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
                    .title("[ToDo] Seleziona il tipo di mappa")
                    .items(R.array.map_providers)
                    .itemsCallbackSingleChoice(0) { _, _, which, _ ->
                        val fragment = FragmentMapsSelector.getGeoJsonFragment(which, geoJson, geoJsonFeatures)
                        FragmentUtils.replace(this@GeoJsonMapsActivity, fragment, containerResId = R.id.mapContainer)
                        true
                    }
                    .positiveText("[ToDo] choose")
                    .show()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
