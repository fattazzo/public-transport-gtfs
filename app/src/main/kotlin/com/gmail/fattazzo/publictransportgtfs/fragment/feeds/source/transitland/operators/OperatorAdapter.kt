package com.gmail.fattazzo.publictransportgtfs.fragment.feeds.source.transitland.operators

import android.content.Context
import android.view.ViewGroup
import com.gmail.fattazzo.feedsources.transitland.domain.Operator
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.RecyclerViewAdapterBase
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EBean
open class OperatorAdapter : RecyclerViewAdapterBase<Operator, OperatorView>() {

    @RootContext
    lateinit var context: Context

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): OperatorView {
        return OperatorView_.build(context)
    }
}