package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitfeed.locations

import android.content.Context
import android.widget.TextView
import com.gmail.fattazzo.feedsources.transitfeed.domain.Location
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EViewGroup(R.layout.item_location)
open class LocationView(context: Context?) : BindableView<Location>(context) {

    @ViewById
    lateinit var nameTV: TextView

    @ViewById
    lateinit var longitudeTV: TextView

    @ViewById
    lateinit var latitudeTV: TextView

    override fun bind(item: Location) {
        nameTV.text = item.name

        latitudeTV.text = item.latitude.toPlainString()
        longitudeTV.text = item.longitude.toPlainString()
    }

}