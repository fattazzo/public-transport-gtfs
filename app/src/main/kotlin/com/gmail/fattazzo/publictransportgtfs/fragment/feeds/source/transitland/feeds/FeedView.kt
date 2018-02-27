package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.feeds

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.gmail.fattazzo.feedsources.GeoJson
import com.gmail.fattazzo.feedsources.transitland.domain.Feed
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.activity.maps.GeoJsonMapsActivity_
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById
import java.text.DateFormat

/**
 * @author fattazzo
 *         <p/>
 *         date: 07/02/18
 */
@EViewGroup(R.layout.item_feed_transitland)
open class FeedView(context: Context?) : BindableView<Feed>(context) {

    @ViewById
    lateinit var oneStopIDTV: TextView

    @ViewById
    lateinit var createdAtTV: TextView

    @ViewById
    lateinit var updatedAtTV: TextView

    @ViewById
    lateinit var geoJsonMapButton: ImageButton

    private var geoJson: GeoJson? = null

    override fun bind(item: Feed) {
        oneStopIDTV.text = item.onestopId

        createdAtTV.text = DateFormat.getDateTimeInstance().format(item.createdAt)
        updatedAtTV.text = DateFormat.getDateTimeInstance().format(item.updatedAt)

        geoJson = GeoJson.fromFeed(item)
        geoJsonMapButton.visibility = if (geoJson != null) View.VISIBLE else View.GONE
    }

    @Click
    fun geoJsonMapButtonClicked() {
        if (geoJson != null)
            GeoJsonMapsActivity_.intent(context).geoJson(geoJson).start()
    }
}