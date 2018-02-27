package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.operators

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.gmail.fattazzo.feedsources.GeoJson
import com.gmail.fattazzo.feedsources.transitland.LocationsManager
import com.gmail.fattazzo.feedsources.transitland.domain.Operator
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.activity.maps.GeoJsonMapsActivity_
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import com.gmail.fattazzo.publictransportgtfs.gtfs.importer.GtfsImporterService
import com.gmail.fattazzo.publictransportgtfs.gtfs.importer.GtfsImporterService_
import com.gmail.fattazzo.publictransportgtfs.utils.Utils
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById


/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EViewGroup(R.layout.item_operator)
open class OperatorView(context: Context?) : BindableView<Operator>(context) {

    @ViewById
    lateinit var nameTV: TextView

    @ViewById
    lateinit var websiteTV: TextView

    @ViewById
    lateinit var locationTV: TextView

    @ViewById
    lateinit var websiteButton: ImageButton

    @Bean
    lateinit var utils: Utils

    @Bean
    lateinit var locationsManager: LocationsManager

    private lateinit var operator: Operator

    override fun bind(item: Operator) {

        operator = item

        nameTV.text = item.name
        websiteTV.text = item.website
        if (websiteTV.text.isNotBlank()) {
            websiteTV.visibility = View.VISIBLE
            websiteButton.visibility = View.VISIBLE
        } else {
            websiteTV.visibility = View.GONE
            websiteButton.visibility = View.GONE
        }

        val location = locationsManager.getLocationByCode(item.state.orEmpty())
        locationTV.text = location.name
        locationTV.visibility = if (locationTV.text.isNotBlank()) View.VISIBLE else View.GONE
    }

    @Click
    fun geoJsonMapButtonClicked() {
        GeoJsonMapsActivity_.intent(context).geoJson(GeoJson.fromOperator(operator)).start()
    }

    @Click
    fun websiteButtonClicked() {
        if (websiteTV.text.isNotBlank())
            utils.openLink(websiteTV.text.toString())
    }

    @Click
    fun downloadButtonClicked() {
        if (!GtfsImporterService.running) {
            GtfsImporterService_.intent(context).importa(operator.onestopId).start()
        } else {
            Toast.makeText(context, "[ToDo] Attendere, un'altra importaizone è in corso", Toast.LENGTH_SHORT).show()
        }
    }
}