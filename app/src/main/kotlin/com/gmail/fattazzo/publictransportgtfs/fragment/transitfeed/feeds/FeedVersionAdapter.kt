package com.gmail.fattazzo.publictransportgtfs.fragment.transitfeed.feeds

import android.content.Context
import com.gmail.fattazzo.publictransportgtfs.adapter.BaseAdapterExt
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import com.gmail.fattazzo.publictransportgtfs.transitfeed.domain.FeedVersion
import org.androidannotations.annotations.EBean

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EBean
open class FeedVersionAdapter : BaseAdapterExt<FeedVersion>() {

    override fun buildView(context: Context): BindableView<FeedVersion> {
        return FeedVersionView_.build(context,0)
    }
}