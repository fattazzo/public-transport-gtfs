package com.gmail.fattazzo.publictransportgtfs.fragment.search.stops

import android.content.Context
import com.gmail.fattazzo.gtfsdb.entities.Stop
import com.gmail.fattazzo.publictransportgtfs.adapter.BaseAdapterExt
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import org.androidannotations.annotations.EBean

/**
 * @author fattazzo
 *         <p/>
 *         date: 09/03/18
 */
@EBean
open class StopsListAdapter : BaseAdapterExt<Stop>() {

    override fun buildView(context: Context): BindableView<Stop> {
        return StopView_.build(context)
    }
}