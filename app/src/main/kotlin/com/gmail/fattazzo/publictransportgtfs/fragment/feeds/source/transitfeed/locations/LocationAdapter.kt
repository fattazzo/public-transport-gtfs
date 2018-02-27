package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitfeed.locations

import android.content.Context
import android.view.ViewGroup
import com.gmail.fattazzo.feedsources.transitfeed.domain.Location
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.RecyclerViewAdapterBase
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EBean
open class LocationAdapter : RecyclerViewAdapterBase<Location, LocationView>() {

    @RootContext
    lateinit var context: Context

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): LocationView {
        return LocationView_.build(context)
    }
}