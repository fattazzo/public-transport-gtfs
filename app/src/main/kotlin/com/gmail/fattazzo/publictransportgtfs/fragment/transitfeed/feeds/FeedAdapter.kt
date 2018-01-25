package com.gmail.fattazzo.publictransportgtfs.fragment.transitfeed.feeds

import android.content.Context
import android.view.ViewGroup
import com.gmail.fattazzo.publictransportgtfs.transitfeed.domain.Feed
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.RecyclerViewAdapterBase
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EBean
open class FeedAdapter : RecyclerViewAdapterBase<Feed, FeedView>() {

    @RootContext
    lateinit var context: Context

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): FeedView {
        return FeedView_.build(context)
    }
}