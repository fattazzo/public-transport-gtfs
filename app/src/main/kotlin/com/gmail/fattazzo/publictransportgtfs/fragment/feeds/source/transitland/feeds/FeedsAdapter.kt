package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.feeds

import android.content.Context
import android.view.ViewGroup
import com.gmail.fattazzo.feedsources.transitland.domain.Feed
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.RecyclerViewAdapterBase
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

/**
 * @author fattazzo
 *         <p/>
 *         date: 07/02/18
 */
@EBean
open class FeedsAdapter : RecyclerViewAdapterBase<Feed, FeedView>() {

    @RootContext
    lateinit var context: Context

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): FeedView {
        return FeedView_.build(context)
    }
}