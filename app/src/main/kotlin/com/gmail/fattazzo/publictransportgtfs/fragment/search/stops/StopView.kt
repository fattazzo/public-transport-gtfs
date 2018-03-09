package com.gmail.fattazzo.publictransportgtfs.fragment.search.stops

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.gmail.fattazzo.gtfsdb.entities.Stop
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.adapter.recycler.BindableView
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

/**
 * @author fattazzo
 *         <p/>
 *         date: 09/03/18
 */
@EViewGroup(R.layout.item_stop)
open class StopView(context: Context?) : BindableView<Stop>(context) {

    @ViewById
    lateinit var stopNameTV: TextView

    @ViewById
    lateinit var latLonTV: TextView

    @ViewById
    lateinit var descriptionTV: TextView

    @ViewById
    lateinit var selectionCheckBox: CheckBox

    @SuppressLint("SetTextI18n")
    override fun bind(item: Stop) {
        stopNameTV.text = item.name

        val lat = resources.getString(R.string.lat)
        val lon = resources.getString(R.string.lon)
        latLonTV.text = "${lat}:${item.latitude.toPlainString()} ${lon}:${item.longitude.toPlainString()}"

        descriptionTV.visibility = View.GONE
        item.description.let {
            descriptionTV.text = item.description!!
        }

        selectionCheckBox.isChecked = item.selected
    }
}