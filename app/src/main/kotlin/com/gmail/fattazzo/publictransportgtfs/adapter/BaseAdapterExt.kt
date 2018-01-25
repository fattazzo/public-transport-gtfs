package com.gmail.fattazzo.publictransportgtfs.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext


/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
@EBean
abstract class BaseAdapterExt<T> : BaseAdapter() {

    @RootContext
    lateinit var rootContext: Context

    var items: List<T> = listOf()

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val bindableView: BindableView<T> = if (convertView == null) {
            buildView(rootContext)
        } else {
            convertView as BindableView<T>
        }
        bindableView.bind(getItem(position))
        return bindableView
    }

    protected abstract fun buildView(context: Context): BindableView<T>
}