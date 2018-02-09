package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.operators

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.activity.maps.GeoJsonMapsActivity_
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import com.gmail.fattazzo.publictransportgtfs.feeds.source.GeoJson
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Operator
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
    lateinit var geoJsonMapButton: ImageButton

    @ViewById
    lateinit var websiteButton: ImageButton

    @Bean
    lateinit var utils: Utils

    private var geoJson: GeoJson? = null

    override fun bind(item: Operator) {

        nameTV.text = item.name
        websiteTV.text = item.website
        if (websiteTV.text.isNotBlank()) {
            websiteTV.visibility = View.VISIBLE
            websiteButton.visibility = View.VISIBLE
        } else {
            websiteTV.visibility = View.GONE
            websiteButton.visibility = View.GONE
        }

        geoJson = GeoJson.fromOperator(item)
        geoJsonMapButton.visibility = if (geoJson != null) View.VISIBLE else View.GONE
    }

    @Click
    fun geoJsonMapButtonClicked() {
        if (geoJson != null)
            GeoJsonMapsActivity_.intent(context).geoJson(geoJson).start()
    }

    @Click
    fun websiteButtonClicked() {
        if (websiteTV.text.isNotBlank())
            utils.openLink(websiteTV.text.toString())
    }
}